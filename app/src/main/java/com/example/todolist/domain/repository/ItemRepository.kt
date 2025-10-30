package com.example.todolist.domain.repository

import com.example.todolist.domain.entity.Item

interface ItemRepository {

    suspend fun  insertItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun getItems(item: Item): List<Item>

    suspend fun removeItem(item: Item)
}