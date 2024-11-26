package com.example.fetchproject

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.domain.ItemsRepository
import com.example.fetchproject.ui.screen.ItemsListScreenViewModel
import com.example.fetchproject.ui.transformer.ItemViewStateTransformer
import com.example.fetchproject.ui.viewstate.ItemsListScreenViewState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemsListScreenViewModelTest {
    private val repository = mockk<ItemsRepository>()
    private val itemViewStateTransformer = mockk<ItemViewStateTransformer>()
    private lateinit var viewModel: ItemsListScreenViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetches data success on init`() = runTest{
        Dispatchers.setMain(Dispatchers.Unconfined)

        // Arrange
        val mockItems = mockk<List<ItemData>>()
        coEvery { repository.getItems() } returns mockItems
        val mockTransformed = mockk<ItemsListScreenViewState>()
        every { itemViewStateTransformer.transform(mockItems) } returns mockTransformed

        // Act
        viewModel = ItemsListScreenViewModel(
            repository = repository,
            itemViewStateTransformer = itemViewStateTransformer,
        )

        // Assert
        assertEquals(mockTransformed, viewModel.viewState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetches data error on init`() = runTest{
        Dispatchers.setMain(Dispatchers.Unconfined)

        // Arrange
        val error = Exception("Test - something went wrong")
        coEvery { repository.getItems() } throws error

        // Act
        viewModel = ItemsListScreenViewModel(
            repository = repository,
            itemViewStateTransformer = itemViewStateTransformer,
        )

        // Assert
        assertEquals(ItemsListScreenViewState.Error, viewModel.viewState.value)
    }
}