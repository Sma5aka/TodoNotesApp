package com.example.myweatherapp.presentation.ui.add_edit_todo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.myweatherapp.presentation.ui.add_edit_todo.AddEditEvent

@Composable
fun CustomBasicTextField(
    mainText: String,
    modifier: Modifier,
    textStyle: TextStyle,
    placeholder: String,
    placeholderTextStyle: TextStyle,
    onEvent: (AddEditEvent) -> Unit
) {
    Box {
        BasicTextField(
            textStyle = textStyle,
            value = mainText.toString(),
            onValueChange = {
                onEvent(AddEditEvent.onTitleChange(mainText.toString()))
            },
            modifier = modifier
        )
        if (mainText.toString().isEmpty()) {
            Text(
                text = placeholder,
                style = placeholderTextStyle
            )
        }
    }

}