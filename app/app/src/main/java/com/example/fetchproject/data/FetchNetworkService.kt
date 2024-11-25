package com.example.fetchproject.data

import retrofit2.http.GET

interface FetchNetworkService {
    @GET("hiring.json")
    suspend fun getItems(): List<ItemApiResponse>
}