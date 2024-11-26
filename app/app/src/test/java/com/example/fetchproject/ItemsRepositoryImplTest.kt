package com.example.fetchproject

import com.example.fetchproject.data.FetchNetworkService
import com.example.fetchproject.data.ItemApiResponse
import com.example.fetchproject.data.ItemDomainTransformer
import com.example.fetchproject.data.ItemsRepositoryImpl
import com.example.fetchproject.domain.ItemData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ItemsRepositoryImplTest {

    private val networkService = mockk<FetchNetworkService>()
    private val itemDomainTransformer = mockk<ItemDomainTransformer>()
    private lateinit var repo: ItemsRepositoryImpl

    @Before
    fun setup() {
        repo = ItemsRepositoryImpl(
            networkService = networkService,
            itemDomainTransformer = itemDomainTransformer,
        )
    }

    @Test
    fun `get items success returns transformed data`() = runTest {
        // Arrange
        val mockApiResponse = mockk<List<ItemApiResponse>>()
        coEvery { networkService.getItems() } returns mockApiResponse
        val mockTransformed = mockk<List<ItemData>>()
        every { itemDomainTransformer.transform(mockApiResponse) } returns mockTransformed

        // Act
        val actual = repo.getItems()

        // Assert
        assertEquals(mockTransformed, actual)
    }

    @Test
    fun `get items failure just throws`() = runTest {
        // Arrange
        val error = Exception("Test - Something went wrong")
        coEvery { networkService.getItems() } throws error

        // Act
        try {
            repo.getItems()
        } catch (e: Exception) {
            assertEquals(e, error)
        }

        // Assert
        verify(exactly = 0) { itemDomainTransformer.transform(any()) }
    }
}