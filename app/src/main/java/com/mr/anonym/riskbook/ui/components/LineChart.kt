package com.mr.anonym.riskbook.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun LineChart(
    backgroundColor: Color,
    points: List<Point>,
    labels:List<String>
) {
    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(50.dp)
        .backgroundColor(backgroundColor)
        .steps(points.size-1)
        .labelData { i-> labels[i] }
        .startPadding(15.dp)
//        .startDrawPadding(15.dp)
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .shouldDrawAxisLineTillEnd(true)
        .backgroundColor(backgroundColor)
//        .labelAndAxisLinePadding(15.dp)
        .labelData { i->
            val yMin = points.minOf { it.y }
            val yMax = points.maxOf { it.y }
            val yScale = ( yMax - yMin ) /steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    LineStyle(),
                    selectionHighlightPoint = SelectionHighlightPoint(),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = backgroundColor
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}