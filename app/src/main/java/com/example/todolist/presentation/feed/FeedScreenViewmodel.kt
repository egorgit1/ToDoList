package com.example.todolist.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.entity.Item
import com.example.todolist.data.repository.ItemRepositoryImpl
import com.example.todolist.domain.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewmodel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FeedScreenState())

    val state = _state.asStateFlow()

    private var items: List<Item> = emptyList()

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.SearchQueryChanged -> onSearchQueryChanged(event.newQuery)
        }
    }

    private fun onSearchQueryChanged(newQuery: String) {
        _state.update { it.copy(searchQuery = newQuery) }

        viewModelScope.launch {
            _state.update { it.copy(filteredItems = filterItems(newQuery, items)) }
        }
    }

    private suspend fun filterItems(query: String, items: List<Item>): List<Item> {
        return withContext(Dispatchers.Default) {
            if (query.isEmpty()) items
            else items.filter { it.description.lowercase().contains(query.lowercase()) }
        }
    }

    init {
        getItems()
    }

    private fun getItems() = viewModelScope.launch {
        val items = withContext(Dispatchers.Default) { itemRepository.getItems() }
        this@FeedScreenViewmodel.items = items
        _state.update { it.copy(filteredItems = filterItems(state.value.searchQuery, items)) }
    }
}

