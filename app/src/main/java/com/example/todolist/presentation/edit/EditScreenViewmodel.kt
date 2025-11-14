package com.example.todolist.presentation.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.todolist.data.repository.ItemRepositoryImpl
import com.example.todolist.domain.repository.ItemRepository
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
            _state.update { it.copy(title = item.title, description = item.description) }
        }
    }


    fun onEvent(event: EditScreenEvent) {
        when (event) {
            is EditScreenEvent.BackToFeedBtnClicked -> onBackBtnClicked(event.id)
            is EditScreenEvent.DescriptionChanged -> onDescriptionChanged(event.newDescription)
            is EditScreenEvent.TitleChanged -> onTitleChanged(event.newTitle)
        }
    }

    private fun onTitleChanged(newTitle: String) {
        _state.update { it.copy(title = newTitle) }
    }

    private fun onDescriptionChanged(newDescription: String) {
        _state.update { it.copy(description = newDescription) }
    }

    private fun onBackBtnClicked(id: Int) {
        val title = state.value.title
        val description = state.value.description

        if (title.isEmpty() && description.isEmpty())
            viewModelScope.launch { itemRepository.removeItemById(id) }
        else {
            viewModelScope.launch {
                itemRepository.updateItemTitle(id, title)
                itemRepository.updateItemDescription(id, description)
            }
        }

    }
}