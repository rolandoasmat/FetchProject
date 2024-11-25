package com.example.fetchproject.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fetchproject.ui.viewstate.ItemViewState

@Composable
fun ListItemCell(
    viewState: ItemViewState
) {
    Text(
        text = viewState.nameLabel,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
    )
}