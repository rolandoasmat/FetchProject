package com.example.fetchproject.di

import com.example.fetchproject.data.ItemsRepositoryImpl
import com.example.fetchproject.domain.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideItemsRepository(
        repo: ItemsRepositoryImpl
    ): ItemsRepository
}