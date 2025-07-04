package com.example.sampleapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GraphScreen(
    viewModel: GraphViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top, // Changed from Center to Top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val itemsData by viewModel.graphData.collectAsState()
        when (itemsData) {
            is UiState.Loading -> {
                Text(text = "Loading")
            }

            is UiState.Success<*> -> {
                Text(text = "Success")
                val data = (itemsData as UiState.Success).data
                ChatWithBinding(data)
            }

            is UiState.Error -> {
                Text(text = "Error")
            }

            is UiState.Empty -> {
                Text(text = "Empty")
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // Pushes the button to the bottom
        Button(
            onClick = { viewModel.getMarketData() },
            modifier = Modifier.padding(bottom = 60.dp)
        ) {
            Text("Generate Random")
        }
    }
}

