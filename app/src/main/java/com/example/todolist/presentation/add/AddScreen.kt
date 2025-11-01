package com.example.todolist.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.presentation.navigation.Screen

@Composable
fun AddScreen(
    navigate: (Screen) -> Unit,
) {

    val viewmodel = hiltViewModel<AddScreenViewmodel>()
    val state by viewmodel.state.collectAsStateWithLifecycle()


}