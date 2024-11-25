package com.example.fetchproject.ui.viewstate

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface ItemsListScreenViewState {

    @Immutable
    class Ready(
        val groups: List<ItemsGroupViewState>,
    ): ItemsListScreenViewState

    @Immutable
    data object Loading: ItemsListScreenViewState

    @Immutable
    data object Error: ItemsListScreenViewState
}