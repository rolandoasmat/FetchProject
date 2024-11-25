package com.example.fetchproject.data

import com.example.fetchproject.domain.ItemData
import com.example.fetchproject.domain.ItemsRepository

class ItemsRepositoryImpl: ItemsRepository {
    override suspend fun getItems(): List<ItemData> {
        return emptyList()
    }
}