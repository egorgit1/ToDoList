package com.example.todolist.presentation.add

import com.example.todolist.domain.entity.Item

sealed interface AddScreenEvent {
    data object BackToFeedBtnClicked : AddScreenEvent
    data class TitleChanged(val newTitle: String) : AddScreenEvent
    data class DescriptionChanged(val newDescription: String) : AddScreenEvent
}