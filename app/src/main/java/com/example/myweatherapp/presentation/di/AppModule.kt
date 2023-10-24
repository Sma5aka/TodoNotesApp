package com.example.myweatherapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.myweatherapp.data.data_source.TodoDatabase
import com.example.myweatherapp.domain.repository.TodoRepository
import com.example.myweatherapp.data.repository.TodoRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application) : TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImplementation(db.dao)
    }

}