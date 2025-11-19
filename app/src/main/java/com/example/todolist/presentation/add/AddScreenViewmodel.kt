package com.example.todolist.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewmodel @Inject constructor(
    val itemRepository: ItemRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddScreenState())

    val state = _state.asStateFlow()

    fun onEvent(event: AddScreenEvent) {
        when (event) {
            is AddScreenEvent.BackToFeedBtnClicked -> onBtnClicked()
            is AddScreenEvent.DescriptionChanged -> onDescriptionChanged(event.newDescription)
            is AddScreenEvent.ColorChanged -> onColorChanged(event.newColor)
            is AddScreenEvent.ExpandChanged -> onExpandChanged(event.newExpand)
        }
    }

    private fun onDescriptionChanged(newDescription: String) {
        _state.update { it.copy(description = newDescription) }
    }

    private fun onColorChanged(newColor: Int) {
        _state.update { it.copy(color = newColor) }
    }

    private fun onExpandChanged(newExpand: Boolean) {
        _state.update { it.copy(isExpanded = newExpand) }
    }

    private fun onBtnClicked() {
        val description = state.value.description
        val color = state.value.color

        if (!description.isEmpty()&&color!=0)
            viewModelScope.launch { itemRepository.insertItem(description, color) }
    }

}