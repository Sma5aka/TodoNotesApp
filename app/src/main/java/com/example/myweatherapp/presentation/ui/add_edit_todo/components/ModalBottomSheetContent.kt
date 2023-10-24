package com.example.myweatherapp.presentation.ui.add_edit_todo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.R

@Composable
fun ModalBottomSheetContent() {
    RadioButtons()
}

@Composable
fun RadioButtons() {
    val colorsList = listOf(
        colorResource(id = R.color.soft_white),
        colorResource(id = R.color.marine),
        colorResource(id = R.color.soft_gray),
        colorResource(id = R.color.soft_yellow),
        colorResource(id = R.color.soft_brown)
    )
    val radioButtons = remember {
        mutableListOf(
            ToggleableInfo(
                isChecked = false,
                color = colorsList[0]
            ),
            ToggleableInfo(
                isChecked = false,
                color = colorsList[1]
            ),
            ToggleableInfo(
                isChecked = false,
                color = colorsList[2]
            ),
            ToggleableInfo(
                isChecked = false,
                color = colorsList[3]
            ),
            ToggleableInfo(
                isChecked = false,
                color = colorsList[4]
            ),
        )
    }

    radioButtons.forEachIndexed { index, info ->
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.color == info.color
                        )
                    }
                }
                .padding(end = 16.dp)
        ) {
            RadioButton(
                selected = info.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.color == info.color
                        )
                    }
                }
            )
        }
    }

}

data class ToggleableInfo(
    val isChecked: Boolean,
    val color: Color
)