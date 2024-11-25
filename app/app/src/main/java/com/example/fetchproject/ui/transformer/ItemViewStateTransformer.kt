package com.example.fetchproject.ui.transformer

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.ui.viewstate.ItemViewState
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState
import javax.inject.Inject

class ItemViewStateTransformer @Inject constructor() {
    fun transform(
        data: List<ItemData>
    ): ItemsListScreenViewState {
        val viewStates = data.map {
            ItemViewState(name = it.name)
        }
        return ItemsListScreenViewState.Ready(viewStates)
    }
}