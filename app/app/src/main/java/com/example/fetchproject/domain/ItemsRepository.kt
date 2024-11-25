package com.example.fetchproject.domain

interface ItemsRepository {
    suspend fun getItems(): List<ItemData>
}