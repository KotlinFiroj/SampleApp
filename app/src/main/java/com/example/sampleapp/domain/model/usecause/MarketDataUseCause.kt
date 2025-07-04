package com.example.sampleapp.domain.model.usecause

import com.example.sampleapp.domain.model.MarketData
import com.example.sampleapp.domain.model.repository.MarketDataRepositoryImpl
import com.example.sampleapp.presentation.UiState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class MarketDataUseCause @Inject constructor(private val repository: MarketDataRepositoryImpl) {

    suspend operator fun invoke(): Flow<UiState<List<MarketData>>> {
        return repository.getMarketData()
    }

}