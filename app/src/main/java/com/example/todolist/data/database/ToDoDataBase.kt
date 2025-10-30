package com.example.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.domain.dao.ItemDao
import com.example.todolist.domain.entity.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ToDoDataBase: RoomDatabase() {
    abstract fun getDao(): ItemDao
}