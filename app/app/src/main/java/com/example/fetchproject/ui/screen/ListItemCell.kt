package com.example.fetchproject.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fetchproject.ui.viewstate.ItemViewState

@Composable
fun ListItemCell(
    viewState: ItemViewState
) {
    Text(text = viewState.name)
}