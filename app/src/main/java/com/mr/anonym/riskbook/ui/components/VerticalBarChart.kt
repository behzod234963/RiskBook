package com.mr.anonym.riskbook.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun VerticalBarChart(
    profits: List<Float>,
    labels: List<String>
) {

    if ( profits.isEmpty() || labels.isEmpty() ) return

    val modelProducer = remember { ChartEntryModelProducer() }

    LaunchedEffect(profits) {
        val entries = profits.mapIndexed { index, value ->
            FloatEntry(index.toFloat(), value)
        }
        modelProducer.setEntries(entries)
    }
    Chart(
        chart = columnChart(
            columns = listOf(
                LineComponent(
                    thicknessDp = 2f,
                    color = colorSelector(4).toArgb(),
                )
            )
        ),
        chartModelProducer = modelProducer,
        startAxis = rememberStartAxis(),
        bottomAxis = rememberBottomAxis(
            valueFormatter = { value,_->
                labels.getOrNull(value.toInt()) ?:""
            }
        ),
    )
}