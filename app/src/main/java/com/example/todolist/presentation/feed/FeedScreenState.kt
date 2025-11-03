package com.example.todolist.presentation.feed

import com.example.todolist.domain.entity.Item

data class FeedScreenState(
    val searchQuery: String = "",
    val filteredItems: List<Item> = emptyList()
)