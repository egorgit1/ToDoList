package com.example.todolist.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.domain.entity.Item

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: Item)

    @Query("UPDATE item set description =:description WHERE id =:id")
    suspend fun updateItemDescById(id: Int, description: String)

    @Query("UPDATE item set color =:color WHERE id =:id")
    suspend fun updateItemColorById(id: Int, color: Int)

    @Query("DELETE FROM item WHERE id =:id")
    suspend fun removeItemById(id: Int)

    @Query("SELECT * FROM item WHERE id =:id")
    suspend fun getItemById(id: Int): Item

    @Query("SELECT * FROM item")
    suspend fun getItems(): List<Item>
}