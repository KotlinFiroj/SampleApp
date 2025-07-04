package com.example.sampleapp.domain.model.repository

import com.example.sampleapp.data.MarketDataDataSource
import com.example.sampleapp.data.repository.MarketDataRepository
import com.example.sampleapp.domain.model.MarketData
import com.example.sampleapp.presentation.UiState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class MarketDataRepositoryImpl @Inject constructor(val dataSource: MarketDataDataSource): MarketDataRepository {

    override suspend fun getMarketData(): Flow<UiState<List<MarketData>>> {
        return dataSource.getMarketData()
    }


}