package com.example.todolist.presentation.feed

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.entity.Item
import com.example.todolist.domain.repository.ItemRepository
import com.example.todolist.domain.repository.ItemRepositoryImpl
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
    private val itemRepository: ItemRepositoryImpl
): ViewModel() {

    private val _state = MutableStateFlow(FeedScreenState())

    private val state = _state.asStateFlow()

    private var items: List<Item> = emptyList()

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.SearchQueryChanged -> onSearchQueryChanged(event.newQuery)
        }
    }

    private fun onSearchQueryChanged(newQuery: String) {
        _state.update { it.copy(searchQuery = newQuery) }

        viewModelScope.launch {
            _state.update { it.copy(filteredItems = filterNews(newQuery, items)) }
        }
    }

    private suspend fun filterNews(query: String, items: List<Item>): List<Item> {
        return withContext(Dispatchers.Default) {
            if (query.isEmpty()) items
            else items.filter { it.title.lowercase().contains(query.lowercase()) }
        }
    }
}