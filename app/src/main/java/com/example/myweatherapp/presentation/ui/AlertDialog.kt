package com.example.myweatherapp.presentation.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun AlertDialogCustom(
    onConfirm: () -> Unit
) {

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                Text(text = "Confirm deletion",
                color = Color.Black)
                    },
            text = {
                Text(text = "Are you sure?",
                color = Color.Black)
                   },

            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                        openDialog.value = false
                    }) {
                    Text(text = "Yes", color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(text = "No", color = Color.Black)
                }
            }
        )
    }
}