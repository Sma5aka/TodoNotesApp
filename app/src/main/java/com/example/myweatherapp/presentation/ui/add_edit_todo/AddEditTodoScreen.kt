package com.example.myweatherapp.presentation.ui.add_edit_todo

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myweatherapp.data.Todo
import com.example.myweatherapp.presentation.ui.add_edit_todo.components.ModalBottomSheetContent
import com.example.myweatherapp.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewmodel = hiltViewModel(),
    todoColor: Int
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val todoBackgroundAnimatable = remember {
        Animatable(
            Color(if(todoColor != -1) todoColor else viewModel.todoColor.value )
        )
    }
    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { TODO() }) {
                        Icon(
                            Icons.Filled.Face,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = {
                        showBottomSheet = true
                    }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        viewModel.onEvent(AddEditEvent.OnSaveTodoClick)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Save"
                        )
                    }
                }
            )
                    },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(todoBackgroundAnimatable.value)
                    .padding(16.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Todo.todosColor.forEach { color ->
                        val colorInt = color.toArgb()
                        Box (
                            modifier = Modifier
                                .size(50.dp)
                                .shadow(15.dp, CircleShape)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = 3.dp,
                                    color = if(viewModel.todoColor.value == colorInt) {
                                        Color.Black
                                    } else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    scope.launch {
                                        todoBackgroundAnimatable.animateTo(
                                            targetValue = Color(colorInt),
                                            animationSpec = tween(durationMillis = 500)
                                        )
                                    }
                                    viewModel.onEvent(AddEditEvent.onChangeColor(colorInt))
                                }
                        ) {

                        }
                    }

                }
                Box {
                    BasicTextField(
                        textStyle = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        value = viewModel.title,
                        onValueChange = {
                            viewModel.onEvent(AddEditEvent.onTitleChange(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (viewModel.title.isEmpty()) {
                        Text(
                            text = "Title",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                            )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Box {
                    BasicTextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        value = viewModel.description,
                        onValueChange = {
                            viewModel.onEvent(AddEditEvent.onDescChange(it))
                        },
                        modifier = Modifier.fillMaxSize(),
                        singleLine = false
                    )
                    if (viewModel.description.isEmpty()) {
                        Text(
                            text = "Description",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                            )
                    }
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    ModalBottomSheetContent()
                    /*Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Hide bottom sheet")
                    }*/
                }
            }
        }
    )
}