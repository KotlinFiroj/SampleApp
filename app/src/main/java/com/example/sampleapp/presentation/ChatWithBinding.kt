package com.example.sampleapp.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.sampleapp.databinding.HorizontalBarChartBinding
import com.example.sampleapp.domain.model.MarketData
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ChatWithBinding(data: List<MarketData>) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()), // Removed fillMaxSize()
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AndroidView(factory = { context ->
            val binding = HorizontalBarChartBinding.inflate(android.view.LayoutInflater.from(context))
            val chart = binding.chart
            val yearList = mutableListOf<String>()
            val entries = data.mapIndexed { index, marketData ->
                yearList.add(marketData.year)
                BarEntry(index.toFloat(), marketData.value.toFloat())
            }
            val dataSet = BarDataSet(entries, "label").apply {
                color = Color.BLACK
                valueTextColor = Color.BLACK
                valueTextSize = 12f
            }
            chart.data = BarData(dataSet)
            chart.barData.barWidth = 0.85f // Increased bar width for thicker bars
            chart.setFitBars(true)


            chart.xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                setDrawAxisLine(true)
                valueFormatter = IndexAxisValueFormatter(yearList)
                granularity = 1f
                isGranularityEnabled = true
                labelCount = yearList.size // Show every year label
            }
            chart.axisLeft.axisMinimum = 10f
            chart.axisRight.isEnabled = false
            chart.description.isEnabled = false
            chart.animateY(1000)
            chart.invalidate()
            binding.root
        })
    }

}