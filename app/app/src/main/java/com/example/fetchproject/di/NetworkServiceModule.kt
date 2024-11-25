package com.example.fetchproject.di

import com.example.fetchproject.data.FetchNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class NetworkServiceModule {
    @Singleton
    @Provides
    fun provideFetchNetworkService(): FetchNetworkService {
        val httpClient = OkHttpClient().newBuilder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        val service: FetchNetworkService = retrofit.create(FetchNetworkService::class.java)
        return service
    }
}