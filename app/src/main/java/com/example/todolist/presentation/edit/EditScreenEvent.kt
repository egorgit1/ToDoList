package com.example.todolist.presentation.edit

import com.example.todolist.presentation.add.AddScreenEvent


sealed interface EditScreenEvent {

    data class BackToFeedBtnClicked(val id: Int) : EditScreenEvent
    data class DescriptionChanged(val newDescription: String) : EditScreenEvent
    data class ColorChanged(val newColor: Int) : EditScreenEvent
    data class ExpandChanged(val newExpand: Boolean) : EditScreenEvent
}