package com.example.todolist.domain.repository

import com.example.todolist.domain.dao.ItemDao
import com.example.todolist.domain.entity.Item
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    val itemDao: ItemDao
) : ItemRepository {
    override suspend fun insertItem(item: Item) {

        itemDao.insertItem(item)
    }

    override suspend fun updateItem(item: Item) {

        itemDao.updateItem(item)
    }

    override suspend fun getItems(): List<Item> {

        return itemDao.getItems()
    }

    override suspend fun removeItem(item: Item) {

        itemDao.removeItem(item)
    }
}