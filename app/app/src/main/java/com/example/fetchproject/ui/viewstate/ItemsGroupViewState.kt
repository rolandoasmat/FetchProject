package com.example.fetchproject.ui.viewstate

import androidx.compose.runtime.Immutable

@Immutable
data class ItemsGroupViewState(
    val header: HeaderViewState,
    val items: List<ItemViewState>,
)