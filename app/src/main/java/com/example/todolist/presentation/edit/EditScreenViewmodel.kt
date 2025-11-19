package com.example.todolist.presentation.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.todolist.domain.repository.ItemRepository
import com.example.todolist.presentation.add.AddScreenEvent
import com.example.todolist.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val itemRepository: ItemRepository
) : ViewModel() {

    private val args = savedStateHandle.toRoute<Screen.Edit>()
    private val id = args.id
    private val _state = MutableStateFlow(EditScreenState())

    val state = _state.asStateFlow()

    init {
        getItem(id)
    }

    private fun getItem(id: Int) {
        viewModelScope.launch {
            val item = with(Dispatchers.Default) { itemRepository.getItemById(id) }
            _state.update { it.copy(description = item.description) }
        }
    }

    fun onEvent(event: EditScreenEvent) {
        when (event) {
            is EditScreenEvent.BackToFeedBtnClicked -> onBackBtnClicked(event.id)
            is EditScreenEvent.DescriptionChanged -> onDescriptionChanged(event.newDescription)
            is EditScreenEvent.ColorChanged -> onColorChanged(event.newColor)
            is EditScreenEvent.ExpandChanged -> onExpandChanged(event.newExpand)
        }
    }

    private fun onColorChanged(newColor: Int) {
        _state.update { it.copy(color = newColor) }
    }

    private fun onExpandChanged(newExpand: Boolean) {
        _state.update { it.copy(isExpanded = newExpand) }
    }


    private fun onDescriptionChanged(newDescription: String) {
        _state.update { it.copy(description = newDescription) }
    }

    private fun onBackBtnClicked(id: Int) {
        val description = state.value.description
        val color = state.value.color

        if (description.isEmpty())
            viewModelScope.launch { itemRepository.removeItemById(id) }
        else if (color == 0) {
            viewModelScope.launch {
                itemRepository.updateItemDescription(id, description)
            }
        } else {
            viewModelScope.launch {
                itemRepository.updateItemColor(id, color)
                itemRepository.updateItemDescription(id, description)
            }
        }

    }
}