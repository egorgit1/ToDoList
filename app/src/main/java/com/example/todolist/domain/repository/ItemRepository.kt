package com.example.todolist.domain.repository

import com.example.todolist.domain.entity.Item

interface ItemRepository {

    suspend fun insertItem(title: String, description: String)

    suspend fun updateItemTitle(id: Int, title: String)

    suspend fun updateItemDescription(id: Int, description: String)

    suspend fun getItemById(id: Int): Item

    suspend fun getItems(): List<Item>

    suspend fun removeItemById(id: Int)
}