package com.example.fetchproject

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.ui.transformer.ItemViewStateTransformer
import com.example.fetchproject.ui.viewstate.ItemViewState
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ItemViewStateTransformerTest {
    private lateinit var transformer: ItemViewStateTransformer

    @Before
    fun setup() {
        transformer = ItemViewStateTransformer()
    }

    @Test
    fun `no items mapped to empty state`() {
        // Act
        val actual = transformer.transform(emptyList())

        // Assert
        assert(actual is ItemsListScreenViewState.Empty)
    }

    @Test
    fun `items are grouped by list id and are in order`() {
        // Arrange
        val items = listOf(
            ItemData(id = 7, listId = 1, name = "item 7"),
            ItemData(id = 2, listId = 2, name = "item 2"),
            ItemData(id = 3, listId = 3, name = "item 3"),
            ItemData(id = 9, listId = 1, name = "item 9"),
            ItemData(id = 4, listId = 2, name = "item 4"),
        )
        // Act
        val actual = transformer.transform(items)

        // Assert
        assert(actual is ItemsListScreenViewState.Ready)
        val ready = actual as ItemsListScreenViewState.Ready
        assertEquals(ready.groups.size, 3)
        assertEquals(ready.groups[0].header.title, "1")
        assertEquals(ready.groups[0].items.size, 2)
        assertEquals(ready.groups[1].header.title, "2")
        assertEquals(ready.groups[1].items.size, 2)
        assertEquals(ready.groups[2].header.title, "3")
        assertEquals(ready.groups[2].items.size, 1)
    }

    @Test
    fun `items within a group are in order`() {
        // Arrange
        val items = listOf(
            ItemData(id = 7, listId = 1, name = "item 7"),
            ItemData(id = 2, listId = 2, name = "item 2"),
            ItemData(id = 3, listId = 3, name = "item 3"),
            ItemData(id = 9, listId = 1, name = "item 9"),
            ItemData(id = 4, listId = 2, name = "item 4"),
        )
        // Act
        val actual = transformer.transform(items)

        // Assert
        assert(actual is ItemsListScreenViewState.Ready)
        val ready = actual as ItemsListScreenViewState.Ready
        assertEquals(ready.groups[0].items[0], ItemViewState(7, "item 7"))
        assertEquals(ready.groups[0].items[1], ItemViewState(9, "item 9"))
        assertEquals(ready.groups[1].items[0], ItemViewState(2, "item 2"))
        assertEquals(ready.groups[1].items[1], ItemViewState(4, "item 4"))
    }
}