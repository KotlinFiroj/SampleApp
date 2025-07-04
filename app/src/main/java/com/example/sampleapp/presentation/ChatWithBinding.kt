package com.example.sampleapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sampleapp.domain.model.MarketData


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ChatWithBinding(
    data: List<MarketData>, lable: String
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        AndroidVieBinding(factory = ChatBinding::inflate) { binding ->

            val chart = binding.chart

            val dataSets = BarDataSets(data, "Year").apply {
                color = Color.BLUE,
                valueTextColor = Color.BLACK,
                valueTextSize = 12f

            }

            chart.data = BarData(dataSets)
            chart.xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                setDrawAxisLine(true)
                valueFormatter = IndexAxisValueFormatter(lable)
                granularity = 1f
            }
            chart.axisLeft.axisMinimum = 0f
            chart.axisRight.isEnabled = false
            chart.description.isEnabled = false
            chart.animateY(1000)
            chart.invalidate()
        }
    }

}