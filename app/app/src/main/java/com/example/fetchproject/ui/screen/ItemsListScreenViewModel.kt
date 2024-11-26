package com.example.fetchproject.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproject.di.IoDispatcher
import com.example.fetchproject.domain.ItemsRepository
import com.example.fetchproject.ui.transformer.ItemViewStateTransformer
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsListScreenViewModel @Inject constructor(
    private val repository: ItemsRepository,
    private val itemViewStateTransformer: ItemViewStateTransformer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {
    private val _viewState = MutableStateFlow<ItemsListScreenViewState>(ItemsListScreenViewState.Loading)
    val viewState: StateFlow<ItemsListScreenViewState> = _viewState

    init {
        viewModelScope.launch(ioDispatcher) {
            fetchData()
        }
    }

    fun retry() {
        viewModelScope.launch(ioDispatcher) {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        _viewState.emit(ItemsListScreenViewState.Loading)
        try {
            val items = repository.getItems()
            val viewState = itemViewStateTransformer.transform(data = items)
            _viewState.emit(viewState)
        } catch (e: Exception) {
            _viewState.emit(ItemsListScreenViewState.Error)
        }
    }
}