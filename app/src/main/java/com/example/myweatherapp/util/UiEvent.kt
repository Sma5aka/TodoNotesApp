package com.example.myweatherapp.util

sealed class UiEvent{

    object PopBackStack: UiEvent()

    data class Navigate(val route: String): UiEvent()

    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()

    data class ShowAlertDialog(
        val title: String,
        val text: String
    ): UiEvent()
}
