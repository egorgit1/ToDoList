package com.example.todolist.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.entity.Item
import com.example.todolist.domain.repository.ItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewmodel @Inject constructor(
    val itemRepository: ItemRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(AddScreenState())

    val state = _state.asStateFlow()

    fun onEvent(event: AddScreenEvent) {
        when (event) {
            is AddScreenEvent.BackToFeedBtnClicked -> onBtnClicked()
            is AddScreenEvent.DescriptionChanged -> onDescriptionChanged(event.newDescription)
            is AddScreenEvent.TitleChanged -> onTitleChanged(event.newTitle)
        }
    }

    private fun onDescriptionChanged(newDescription: String) {
        _state.update { it.copy(description = newDescription) }
    }

    private fun onTitleChanged(newTitle: String) {
        _state.update { it.copy(title = newTitle) }
    }

    private fun onBtnClicked() {
        val title = state.value.title
        val description = state.value.description

        if (!title.isEmpty() && !description.isEmpty())
            viewModelScope.launch { itemRepository.insertItem(title,description) }
    }

}