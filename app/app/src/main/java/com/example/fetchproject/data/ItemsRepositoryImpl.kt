package com.example.fetchproject.data

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.domain.ItemsRepository
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val networkService: FetchNetworkService,
    private val itemDomainTransformer: ItemDomainTransformer,
) : ItemsRepository {
    override suspend fun getItems(): List<ItemData> {
        val response = networkService.getItems()
        val transformed = itemDomainTransformer.transform(response)
        return transformed
    }
}