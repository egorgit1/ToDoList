package com.example.todolist.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.domain.entity.Item

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun removeItem(item: Item)

    @Query("SELECT * FROM item")
    suspend fun getItems() : List<Item>
}