package com.example.sampleapp.data

import com.example.sampleapp.domain.model.MarketData
import com.example.sampleapp.presentation.UiState
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

class MarketDataDataSource @Inject constructor() {

    val data = MutableStateFlow<UiState<List<MarketData>>>(UiState.Empty)

    suspend fun getMarketData(): Flow<UiState<List<MarketData>>> {
        try {
            data.emit(UiState.Loading)
            delay(500)
            val items = listOf(MarketData("2000", 10.7),
                MarketData("2001", 20.5),
                MarketData("2002", 20.5),
                MarketData("2003", 20.5),
                MarketData("2004", 20.5),
                MarketData("2005", 20.5),
                MarketData("2006", 20.5),
                MarketData("2007", 20.5),
                MarketData("2008", 20.5),
                MarketData("2009", 20.5),
                MarketData("2010", 20.5),
                MarketData("2011", 20.5),
                MarketData("2012", 20.5),
                MarketData("2013", 20.5),
                MarketData("2014", 20.5),
                MarketData("2015", 20.5),
                MarketData("2016", 20.5),
                MarketData("2017", 20.5),
                MarketData("2018", 20.5),
                MarketData("2019", 20.5),
                MarketData("2020", 20.5),
                MarketData("2021", 20.5),
                MarketData("2022", 20.5),
                MarketData("2023", 20.5),
                MarketData("2024", 20.5),
                MarketData("2025", 20.5))
            val randomList = mutableListOf<MarketData>()
            for(i in items.indices) {
                val ramdomValue = Random.nextInt(10, 100).toDouble()
                randomList.add(MarketData(items[i].year, ramdomValue))
            }

            data.emit(UiState.Success(randomList))
        } catch (e: Exception) {
            data.emit(UiState.Error(e.message ?: "Unknown error"))
        }
        return data
    }
}