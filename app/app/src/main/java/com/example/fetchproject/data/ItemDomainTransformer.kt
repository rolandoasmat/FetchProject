package com.example.fetchproject.data

import com.example.fetchproject.domain.ItemData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemDomainTransformer @Inject constructor() {
    fun transform(
        data: List<ItemApiResponse>,
    ): List<ItemData> {
        return data.mapNotNull { it.toDomain() }
    }

    private fun ItemApiResponse.toDomain(): ItemData? {
        return if (this.id != null &&
            this.listId != null &&
            !this.name.isNullOrBlank()
        ) {
            ItemData(id = this.id, listId = this.listId, name = this.name)
        } else {
            null
        }
    }
}