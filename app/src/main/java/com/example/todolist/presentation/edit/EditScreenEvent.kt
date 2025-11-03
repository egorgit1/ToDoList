package com.example.todolist.presentation.edit


sealed interface EditScreenEvent {

    data class BackToFeedBtnClicked(val id: Int) : EditScreenEvent
    data class TitleChanged(val newTitle: String) : EditScreenEvent
    data class DescriptionChanged(val newDescription: String) : EditScreenEvent
}