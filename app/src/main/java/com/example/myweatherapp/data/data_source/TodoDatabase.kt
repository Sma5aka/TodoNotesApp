package com.example.myweatherapp.data.data_source

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myweatherapp.data.Todo

@Database(
    entities = [Todo::class],
    version = 2,
    //exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao

}