package com.example.myapplication.di

import com.example.myapplication.data.AlphabetRepository
import com.example.myapplication.data.AlphabetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    // @Singleton
    fun provideAlphabetRepository(): AlphabetRepository {
        return AlphabetRepositoryImpl()
    }
}