package com.mr.anonym.riskbook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mr.anonym.riskbook.ui.screens.addTransactionScreen.AddTransactionScreen
import com.mr.anonym.riskbook.ui.screens.analyticsScreen.AnalyticsScreen
import com.mr.anonym.riskbook.ui.screens.calculatorScreen.CalculatorScreen
import com.mr.anonym.riskbook.ui.screens.mainScreen.MainScreen
import com.mr.anonym.riskbook.ui.screens.conclusionsScreen.ConclusionsScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreensRouter.MainScreen.route
    ){
        composable (ScreensRouter.MainScreen.route){ MainScreen(navController) }
        composable (
            route = ScreensRouter.AddTransactionScreen.route + "/{id}",
            arguments = listOf(
                navArgument( "id" ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){ entry->
            val id = entry.arguments?.getInt("id")?:-1
            AddTransactionScreen(id, navController)
        }
        composable (ScreensRouter.AnalyticsScreen.route){ AnalyticsScreen(navController) }
        composable(ScreensRouter.CalculatorScreen.route) { CalculatorScreen(navController) }
        composable (ScreensRouter.ConclusionsScreen.route){ ConclusionsScreen(navController) }
    }
}