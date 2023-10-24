package com.example.myweatherapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myweatherapp.presentation.ui.theme.marine
import com.example.myweatherapp.presentation.ui.theme.soft_brown
import com.example.myweatherapp.presentation.ui.theme.soft_gray
import com.example.myweatherapp.presentation.ui.theme.soft_white
import com.example.myweatherapp.presentation.ui.theme.soft_yellow

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @ColumnInfo(name = "color", defaultValue = 0.toString())
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val todosColor = listOf(
            soft_white,
            marine,
            soft_gray,
            soft_yellow,
            soft_brown
        )
    }
}
