package com.mr.anonym.riskbook.ui.screens.analyticsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.riskbook.ui.components.ColorSelector
import com.mr.anonym.riskbook.ui.components.VerticalBarChart

@Composable
fun AnalyticsScreen(
    navController: NavController
) {
    Scaffold(
        containerColor = ColorSelector(0),
        contentColor = ColorSelector(0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
//            VerticalBarChart(
//                textColor = ColorSelector(1),
//                bars =
//            )
        }
    }
}