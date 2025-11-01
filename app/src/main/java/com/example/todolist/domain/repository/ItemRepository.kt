package com.example.todolist.domain.repository

import com.example.todolist.domain.entity.Item

interface ItemRepository {

    suspend fun  insertItem(title: String, description: String)

    suspend fun updateItem(item: Item)

    suspend fun getItems(): List<Item>

    suspend fun removeItem(item: Item)
}