package com.mr.anonym.riskbook.ui.screens.mainScreen

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.presentation.navigation.ScreensRouter
import com.mr.anonym.riskbook.ui.components.colorSelector
import com.mr.anonym.riskbook.ui.components.LineChart

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
//    Context
    val activity = LocalActivity.current

//    List
    val transactions = viewModel.transactions
    val points = viewModel.points
    val labels = viewModel.labels

//    Fonts
    val iosFont = FontFamily(Font(R.font.ios_font))
    val saibaFont = FontFamily(Font(R.font.saiba))

//    UI
    BackHandler{
        activity?.finish()
    }
    Scaffold(
        containerColor = colorSelector(0),
        contentColor = colorSelector(0),
        topBar = {
            MainScreenTopBar(
                containerColor = colorSelector(4),
                textColor = colorSelector(1),
                fontFamily = saibaFont,
                title = stringResource(R.string.app_name),
                onAnalyticsScreen = { navController.navigate(ScreensRouter.AnalyticsScreen.route) },
                onAddClick = { navController.navigate(ScreensRouter.AddTransactionScreen.route + "/-1") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            viewModel.formatPoints()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ){
                item {
                    if ( points.isNotEmpty()){
                        LineChart(
                            backgroundColor = colorSelector(4),
                            points = points,
                            labels
                        )
                    }
                }
                items(transactions.value){ model->
                    MainScreenItem(
                        backgroundColor = colorSelector(1),
                        borderColor = if ( model.position ) colorSelector(4) else colorSelector(3),
                        textColor = colorSelector(0),
                        profitColor = if (model.profit.toString().startsWith('-')) colorSelector(3)
                        else colorSelector(4),
                        fontFamily = iosFont,
                        model = model,
                        onClick = { navController.navigate(ScreensRouter.AddTransactionScreen.route + "/${model.id}") },
                        onDeleteClick = { viewModel.deleteTransaction(model) }
                    )
                }
            }
        }
    }
}