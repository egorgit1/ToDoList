package com.example.todolist.presentation.feed

sealed interface FeedScreenEvent {
    data class SearchQueryChanged(val newQuery: String) : FeedScreenEvent
}