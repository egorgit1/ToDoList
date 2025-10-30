package com.example.todolist.presentation.feed

import androidx.room.Query
import com.example.todolist.domain.entity.Item

data class FeedScreenState(
    val searchQuery: String = "",
    val filteredItems: List<Item> = emptyList()
)