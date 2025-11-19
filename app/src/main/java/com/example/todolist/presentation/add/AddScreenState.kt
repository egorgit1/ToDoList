package com.example.todolist.presentation.add

data class AddScreenState(
    val description: String = "",
    val color: Int = 0,
    val isExpanded: Boolean = false,
)