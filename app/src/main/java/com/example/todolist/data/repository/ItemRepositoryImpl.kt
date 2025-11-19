package com.example.todolist.data.repository

import com.example.todolist.domain.dao.ItemDao
import com.example.todolist.domain.entity.Item
import com.example.todolist.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    val itemDao: ItemDao
) : ItemRepository {
    override suspend fun insertItem(description: String, color: Int) {

        itemDao.insertItem(
            Item(
                0,
                description,
                color
            )
        )
    }

    override suspend fun updateItemDescription(id: Int, description: String) {

        itemDao.updateItemDescById(id, description)
    }

    override suspend fun updateItemColor(id: Int, color: Int) {

        itemDao.updateItemColorById(id, color)
    }

    override suspend fun getItemById(id: Int): Item {

        return itemDao.getItemById(id)
    }

    override suspend fun getItems(): List<Item> {

        return itemDao.getItems()
    }

    override suspend fun removeItemById(id: Int) {

        itemDao.removeItemById(id)
    }
}