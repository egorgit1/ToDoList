package com.example.todolist.presentation.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.ItemRepository
import com.example.todolist.domain.repository.ItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewmodel @Inject constructor(
    val itemRepository: ItemRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(EditScreenState())

    val state = _state.asStateFlow()

    fun onEvent(event: EditScreenEvent) {
        when (event) {
            is EditScreenEvent.BackToFeedBtnClicked -> onBackBtnClicked()
            is EditScreenEvent.DescriptionChanged -> onDescriptionChanged(event.newDescription)
            is EditScreenEvent.TitleChanged -> onTitleChanged(event.newTitle)
        }
    }

    private fun onTitleChanged(newTitle: String) {
        _state.update { it.copy(title = newTitle) }
    }

    private fun onDescriptionChanged(newDescription:String){
        _state.update { it.copy(description = newDescription) }
    }

    private fun onBackBtnClicked() {
        val title = state.value.title
        val description = state.value.description

        if (title.isEmpty() && description.isEmpty())
            viewModelScope.launch { }
    }
}