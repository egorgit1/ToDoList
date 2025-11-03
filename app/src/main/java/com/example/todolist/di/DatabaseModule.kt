package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.database.ToDoDataBase
import com.example.todolist.domain.dao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDataBase {
        return Room.databaseBuilder(
            context,
            ToDoDataBase::class.java,
            "todo.db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemDao(toDoDataBase: ToDoDataBase): ItemDao {
        return toDoDataBase.getDao()
    }
}