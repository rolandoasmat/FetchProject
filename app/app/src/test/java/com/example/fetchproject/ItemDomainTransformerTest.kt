package com.example.fetchproject

import com.example.fetchproject.data.ItemApiResponse
import com.example.fetchproject.data.ItemDomainTransformer
import com.example.fetchproject.domain.ItemData
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ItemDomainTransformerTest {

    @Test
    fun `null name item is ignored`() {
        // Arrange
        val data = listOf(
            ItemApiResponse(id = 1, listId = 2, name = null)
        )

        // Act
        val actual = ItemDomainTransformer().transform(data)

        // Assert
        assertEquals(actual.size, 0)
    }

    @Test
    fun `blank name item is ignored`() {
        // Arrange
        val data = listOf(
            ItemApiResponse(id = 1, listId = 2, name = "   ")
        )

        // Act
        val actual = ItemDomainTransformer().transform(data)

        // Assert
        assertEquals(actual.size, 0)
    }

    @Test
    fun `null id item is ignored`() {
        // Arrange
        val data = listOf(
            ItemApiResponse(id = null, listId = 2, name = "test")
        )

        // Act
        val actual = ItemDomainTransformer().transform(data)

        // Assert
        assertEquals(actual.size, 0)
    }

    @Test
    fun `null list id item is ignored`() {
        // Arrange
        val data = listOf(
            ItemApiResponse(id = 1, listId = null, name = "test")
        )

        // Act
        val actual = ItemDomainTransformer().transform(data)

        // Assert
        assertEquals(actual.size, 0)
    }

    @Test
    fun `valid items are mapped`() {
        // Arrange
        val data = listOf(
            ItemApiResponse(id = 1, listId = 11, name = "test item 1"),
            ItemApiResponse(id = 2, listId = 22, name = "test item 2"),
            ItemApiResponse(id = 3, listId = 33, name = "test item 3"),
        )

        // Act
        val actual = ItemDomainTransformer().transform(data)

        // Assert
        assertEquals(actual.size, 3)
        assertEquals(actual[0], ItemData(1, 11, "test item 1"))
        assertEquals(actual[1], ItemData(2, 22, "test item 2"))
        assertEquals(actual[2], ItemData(3, 33, "test item 3"))
    }
}