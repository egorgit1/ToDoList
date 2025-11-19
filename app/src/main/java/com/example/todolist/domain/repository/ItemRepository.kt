package com.example.todolist.domain.repository

import com.example.todolist.domain.entity.Item
interface ItemRepository {

    suspend fun insertItem(description: String, color: Int)

    suspend fun updateItemDescription(id: Int, description: String)

    suspend fun updateItemColor(id: Int, color: Int)

    suspend fun getItemById(id: Int): Item

    suspend fun getItems(): List<Item>

    suspend fun removeItemById(id: Int)
}