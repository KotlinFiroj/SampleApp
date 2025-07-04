package com.example.sampleapp.di

import com.example.sampleapp.data.MarketDataDataSource
import com.example.sampleapp.data.repository.MarketDataRepository
import com.example.sampleapp.domain.model.repository.MarketDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideMarketDataSource(): MarketDataDataSource {
        return MarketDataDataSource()
    }

    @Provides
    @Singleton
    fun provideMarketDataRepository(dataSource: MarketDataDataSource): MarketDataRepository {
        return MarketDataRepositoryImpl(dataSource)
    }

}