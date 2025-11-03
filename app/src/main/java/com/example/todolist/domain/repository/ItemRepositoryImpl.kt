package com.example.todolist.domain.repository

import com.example.todolist.domain.dao.ItemDao
import com.example.todolist.domain.entity.Item
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    val itemDao: ItemDao
) : ItemRepository {
    override suspend fun insertItem(title: String, description: String) {

        itemDao.insertItem(
            Item(
                0,
                title,
                description
            )
        )
    }

    override suspend fun updateItemTitle(id: Int, title: String) {

        itemDao.updateItemTitleById(id, title)
    }

    override suspend fun updateItemDescription(id: Int, description: String) {

        itemDao.updateItemDescById(id, description)
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