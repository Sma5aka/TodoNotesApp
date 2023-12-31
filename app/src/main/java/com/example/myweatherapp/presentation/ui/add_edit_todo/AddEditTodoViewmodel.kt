package com.example.myweatherapp.presentation.ui.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.data.Todo
import com.example.myweatherapp.domain.repository.TodoRepository
import com.example.myweatherapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewmodel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoColor = mutableStateOf(Todo.todosColor.random().toArgb())
    val todoColor: State<Int> = _todoColor
    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1){
            viewModelScope.launch {
                repository.getTodoById(todoId).let { todo ->
                    title = todo!!.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewmodel.todo = todo
                }

            }
        }

    }

    fun onEvent(event: AddEditEvent) {
        when(event) {
            is AddEditEvent.onTitleChange -> {
                title = event.title
            }
            is AddEditEvent.onDescChange -> {
                description = event.description
            }
            is AddEditEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            color = todo!!.color,
                            id = todo?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
            is AddEditEvent.onChangeColor -> {
                _todoColor.value = event.color
            }
        }
    }

    private fun sendUiEvent( uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

}