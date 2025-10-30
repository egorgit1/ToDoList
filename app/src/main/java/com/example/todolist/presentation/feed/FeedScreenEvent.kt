package com.example.todolist.presentation.feed

import android.adservices.ondevicepersonalization.FederatedComputeInput
import com.example.todolist.domain.entity.Item

sealed interface FeedScreenEvent {
    data class SearchQueryChanged(val newQuery: String) : FeedScreenEvent
}