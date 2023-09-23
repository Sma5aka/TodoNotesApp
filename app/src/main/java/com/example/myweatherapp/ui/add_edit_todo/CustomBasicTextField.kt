package com.example.myweatherapp.ui.add_edit_todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.room.util.appendPlaceholders
import com.example.myweatherapp.util.UiEvent

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