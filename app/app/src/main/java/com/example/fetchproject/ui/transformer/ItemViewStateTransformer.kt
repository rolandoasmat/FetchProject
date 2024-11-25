package com.example.fetchproject.ui.transformer

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.ui.viewstate.HeaderViewState
import com.example.fetchproject.ui.viewstate.ItemViewState
import com.example.fetchproject.ui.viewstate.ItemsGroupViewState
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState
import javax.inject.Inject

class ItemViewStateTransformer @Inject constructor() {
    fun transform(
        data: List<ItemData>
    ): ItemsListScreenViewState {
        val groups = data.groupBy { it.listId }
            .toSortedMap()
            .map { group ->
            val header = HeaderViewState(title = "List id: ${group.key}")
            val items = group.value
                .sortedBy { it.name }
                .map { item ->
                ItemViewState(
                    id = item.id,
                    nameLabel = item.name,
                )
            }
            ItemsGroupViewState(header = header, items = items)
        }
        return ItemsListScreenViewState.Ready(groups = groups)
    }
}