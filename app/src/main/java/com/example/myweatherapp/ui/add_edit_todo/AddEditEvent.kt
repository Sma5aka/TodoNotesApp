package com.example.myweatherapp.ui.add_edit_todo

sealed class AddEditEvent {
    data class onTitleChange(val title: String): AddEditEvent()
    data class onDescChange(val description: String): AddEditEvent()
    object OnSaveTodoClick: AddEditEvent()
}