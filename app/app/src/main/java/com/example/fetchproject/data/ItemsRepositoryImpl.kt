package com.example.fetchproject.data

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.domain.ItemsRepository
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val networkService: FetchNetworkService,
) : ItemsRepository {
    override suspend fun getItems(): List<ItemData> {
        // TODO use api
        return emptyList()
    }
}