package com.example.todolist.presentation.add

sealed interface AddScreenEvent {
    data object BackToFeedBtnClicked : AddScreenEvent
    data class DescriptionChanged(val newDescription: String) : AddScreenEvent
    data class ColorChanged(val newColor: Int) : AddScreenEvent
    data class ExpandChanged(val newExpand: Boolean) : AddScreenEvent
}