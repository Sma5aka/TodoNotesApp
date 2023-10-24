package com.example.myweatherapp.data.repository

import com.example.myweatherapp.data.Todo
import com.example.myweatherapp.data.data_source.TodoDao
import com.example.myweatherapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImplementation (
    private val dao: TodoDao
): TodoRepository {

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}