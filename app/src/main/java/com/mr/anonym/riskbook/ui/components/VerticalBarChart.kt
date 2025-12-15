package com.mr.anonym.riskbook.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters

@Composable
fun VerticalBarChart(
    textColor: Color,
    bars: List<BarParameters>
) {
    BarChart(
        chartParameters = bars,
        isShowGrid = true,
        animateChart = true,
        showGridWithSpacer = true,
        xAxisStyle = TextStyle(
            fontSize = 16.sp,
            color = textColor
        )
    )
}

@Preview
@Composable
private fun PreviewVerticalBarChart() {
    VerticalBarChart(
        textColor = Color.White,
        bars = listOf(
            BarParameters(
                dataName = "Jan",
                data = listOf(10.0),
                barColor = Color.Green
            ),
            BarParameters(
                dataName = "Jan",
                data = listOf(20.0),
                barColor = Color.Green
            ),
            BarParameters(
                dataName = "Jan",
                data = listOf(30.0),
                barColor = Color.Green
            ),
        ),
    )
}