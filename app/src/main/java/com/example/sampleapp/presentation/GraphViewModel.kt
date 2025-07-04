package com.example.sampleapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.domain.model.MarketData
import com.example.sampleapp.domain.model.usecause.MarketDataUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class GraphViewModel @Inject constructor(val useCause: MarketDataUseCause) : ViewModel() {

    private val _graphData = MutableStateFlow<UiState<List<MarketData>>>(UiState.Empty)
    val graphData = _graphData.asStateFlow()

    init {
        getMarketData()
    }

    fun getMarketData() {

        viewModelScope.launch {
            useCause.invoke()
                .catch {
                    _graphData.value = UiState.Error(it.message ?: "Unknown error")
                }
                .collect {
                    _graphData.value = it
                }
        }
    }


}