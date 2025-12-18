package com.mr.anonym.riskbook.presentation.navigation

sealed class ScreensRouter( val route: String) {
    object MainScreen: ScreensRouter("MainScreen")
    object AddTransactionScreen: ScreensRouter("AddTransactionScreen")
    object AnalyticsScreen: ScreensRouter("AnalyticsScreen")
    object CalculatorScreen: ScreensRouter("CalculatorScreen")
    object ConclusionsScreen: ScreensRouter("ConclusionsScreen")
}