package com.example.fetchproject.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetchproject.R
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState

@Composable
fun ItemsListScreen(
    viewModel: ItemsListScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.viewState.collectAsState()
    ItemsListScreen(
        viewState = viewState,
        onRetry = viewModel::retry,
        modifier = modifier.fillMaxSize().padding(16.dp),
    )
}

@Composable
private fun ItemsListScreen(
    viewState: ItemsListScreenViewState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (viewState) {
        is ItemsListScreenViewState.Ready -> {
            ItemsListScreenReady(
                viewState = viewState,
                modifier = modifier,
            )
        }
        ItemsListScreenViewState.Loading -> {
            ItemsListScreenLoading(modifier = modifier)
        }
        ItemsListScreenViewState.Error -> {
            ItemsListScreenError(modifier = modifier, onRetry = onRetry)
        }
        ItemsListScreenViewState.Empty -> {
            ItemsListScreenEmpty(modifier = modifier)
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

@Composable
private fun ItemsListScreenLoading(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator()
            Text(
                text = stringResource(R.string.loading_label),
                textAlign = TextAlign.Center,
                fontSize = ScreenMessageFontSize,
            )
        }
    }
}

@Composable
private fun ItemsListScreenError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.error_label),
                fontSize = ScreenMessageFontSize,
                textAlign = TextAlign.Center,
            )
            Button(onClick = onRetry) {
                Text(
                    text = stringResource(R.string.retry_label),
                    fontSize = ScreenMessageFontSize,
                    textAlign = TextAlign.Center,
                )
            }

        }

    }
}

@Composable
private fun ItemsListScreenEmpty(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = stringResource(R.string.empty_state_label),
            fontSize = ScreenMessageFontSize,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private val ScreenMessageFontSize = 24.sp