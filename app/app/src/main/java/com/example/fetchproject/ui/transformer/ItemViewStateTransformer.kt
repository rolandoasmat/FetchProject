package com.example.fetchproject.ui.transformer

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState

class ItemViewStateTransformer {
    fun transform(
        data: List<ItemData>
    ): ItemsListScreenViewState {
        return ItemsListScreenViewState.Ready(emptyList())
    }
}