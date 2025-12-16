package com.mr.anonym.riskbook.ui.screens.analyticsScreen

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.presentation.navigation.ScreensRouter
import com.mr.anonym.riskbook.ui.components.colorSelector
import com.mr.anonym.riskbook.ui.components.VerticalBarChart

@Composable
fun AnalyticsScreen(
    navController: NavController,
    viewModel: AnalyticsViewModel = hiltViewModel()
) {

//    Object
    val calendar = Calendar.getInstance()

//    Int
    val year = remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    val month = remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val selectedTabIndex = remember { mutableStateOf(month.value) }

//    List
    val profits = viewModel.profits
    val labels = viewModel.labels
    val years = viewModel.years
    val months = viewModel.months
    val monthlyTransactions = viewModel.monthlyTransactions

//    State
    val saibaFontFamily = FontFamily(Font(R.font.saiba))
    val iosFont = FontFamily(Font(R.font.ios_font))

    Scaffold(
        containerColor = colorSelector(0),
        contentColor = colorSelector(0),
        topBar = {
            AnalyticsTopBar(
                backgroundColor = colorSelector(4),
                textColor = colorSelector(1),
                fontFamily = saibaFontFamily,
                onBackClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            LazyRow {
                items(years.value) { value ->
                    YearItem(
                        backgroundColor = colorSelector(6),
                        textColor = colorSelector(1),
                        fontFamily = iosFont,
                        year = value,
                        onClick = {
                            viewModel.getYearlyTransactions(value)
                        }
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            VerticalBarChart(
                profits = profits,
                labels = labels
            )
            Spacer(Modifier.height(10.dp))
            LazyRow {
                items(years.value) { value ->
                    YearItem(
                        backgroundColor = colorSelector(6),
                        textColor = colorSelector(1),
                        fontFamily = iosFont,
                        year = value,
                        onClick = { year.value = value }
                    )
                }
            }
            Spacer(Modifier.height(5.dp))
            AnalyticsScrollableTabRow(
                backgroundColor = colorSelector(0),
                textColor = colorSelector(1),
                selectedTabColor = colorSelector(6),
                fontFamily = iosFont,
                selectedTabIndex = selectedTabIndex.value,
                tabs = months.value,
                onClick = {
                    selectedTabIndex.value = it
                    viewModel.getMonthlyTransactions(
                        year = year.value,
                        month = it
                    )
                },
                content = {
                    LazyColumn {
                        items(monthlyTransactions.value) { model ->
                            MonthlyTransactionsItem(
                                backgroundColor = colorSelector(1),
                                textColor = colorSelector(0),
                                borderColor = if (model.position) colorSelector(4) else colorSelector(
                                    3
                                ),
                                profitColor = if (model.profit.toString().startsWith('-'))
                                    colorSelector(3)
                                else colorSelector(4),
                                fontFamily = iosFont,
                                model = model,
                                onClick = { navController.navigate(ScreensRouter.AddTransactionScreen.route + "/${model.id}") },
                            )
                        }
                    }
                }
            )
        }
    }
}