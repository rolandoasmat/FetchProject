package com.example.fetchproject.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState

@Composable
fun ItemsListScreen(
    viewModel: ItemsListScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.viewState.collectAsState()
    ItemsListScreen(viewState = viewState, modifier = modifier)
}

@Composable
private fun ItemsListScreen(
    viewState: ItemsListScreenViewState,
    modifier: Modifier = Modifier,
) {
    when (viewState) {

        is ItemsListScreenViewState.Ready -> {
            ItemsListScreenReady(viewState = viewState, modifier = modifier)
        }
        ItemsListScreenViewState.Loading -> {
            // TODO loading state
        }
        ItemsListScreenViewState.Error -> {
            // TODO error state
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemsListScreenReady(
    viewState: ItemsListScreenViewState.Ready,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        viewState.groups.forEach { group ->
            stickyHeader(key = group.header.title) {
                GroupHeader(viewState = group.header)
            }
            items(group.items, key = { it.id }) { itemViewState ->
                ListItemCell(itemViewState)
            }
        }
    }
}