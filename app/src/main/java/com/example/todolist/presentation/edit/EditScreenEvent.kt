package com.example.todolist.presentation.edit

sealed interface EditScreenEvent {
    data object BackToFeedBtnClicked : EditScreenEvent
    data class TitleChanged(val newTitle: String) : EditScreenEvent
    data class DescriptionChanged(val newDescription: String) : EditScreenEvent
}