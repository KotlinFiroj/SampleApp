package com.example.sampleapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GraphScreen(
    viewModel: GraphViewModel = hiltViewModel()
) {

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(20.dp))


        val itemsData by viewModel.graphData.collectAsState()

        when (itemsData) {
            is UiState.Loading -> {
                Text(text = "Loading")
            }

            is UiState.Success<*> -> {
                Text(text = "Success")
                val data = (itemsData as UiState.Success).data

                ChatWithBinding(data, lable = "Year")

                LazyColumn {
                    items(data.size) { item ->
                        Row {
                            Text(text = "Year: ${data[item].year}")
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "Value: ${data[item].value}")
                        }
                    }
                }

            }
            is UiState.Error -> {
                Text(text = "Error")
            }

            is UiState.Empty -> {
                Text(text = "Empty")
            }
        }


        Button(onClick = {
            viewModel.getMarketData()
        }) {
            Text("Generate Random")
        }
    }
}

