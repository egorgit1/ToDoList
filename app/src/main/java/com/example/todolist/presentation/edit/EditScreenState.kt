package com.example.todolist.presentation.edit

data class EditScreenState(
    val description: String = "",
    val color: Int = 0,
    val isExpanded: Boolean = false,
)