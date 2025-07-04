package com.example.sampleapp.data.repository

import com.example.sampleapp.domain.model.MarketData
import com.example.sampleapp.presentation.UiState
import kotlinx.coroutines.flow.Flow

interface MarketDataRepository {

    suspend fun getMarketData(): Flow<UiState<List<MarketData>>>


}