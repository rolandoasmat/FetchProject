package com.example.fetchproject.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState

@Composable
fun ItemsListScreen(
    viewModel: ItemsListScreenViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    ItemsListScreen(viewState = viewState)
}

@Composable
private fun ItemsListScreen(
    viewState: ItemsListScreenViewState
) {
    when (viewState) {

        is ItemsListScreenViewState.Ready -> {
            ItemsListScreenReady(viewState = viewState)
        }
        ItemsListScreenViewState.Loading -> {
            // TODO loading state
        }
        ItemsListScreenViewState.Error -> {
            // TODO error state
        }
    }
}

@Composable
private fun ItemsListScreenReady(
    viewState: ItemsListScreenViewState.Ready
) {
    LazyColumn {
        items(viewState.items) { itemViewState ->
            ListItemCell(itemViewState)
        }
    }
}