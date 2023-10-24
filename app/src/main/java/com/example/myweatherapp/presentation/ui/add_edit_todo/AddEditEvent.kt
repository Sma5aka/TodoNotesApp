package com.example.myweatherapp.presentation.ui.add_edit_todo

sealed class AddEditEvent {
    data class onTitleChange(val title: String): AddEditEvent()
    data class onDescChange(val description: String): AddEditEvent()
    data class onChangeColor(val color: Int): AddEditEvent()
    object OnSaveTodoClick: AddEditEvent()
}