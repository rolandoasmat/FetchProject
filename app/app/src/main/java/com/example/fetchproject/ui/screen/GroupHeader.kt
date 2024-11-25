package com.example.fetchproject.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fetchproject.ui.viewstate.HeaderViewState

@Composable
fun GroupHeader(
    viewState: HeaderViewState
) {
    Text(text = viewState.title)
}