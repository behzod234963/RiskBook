package com.mr.anonym.riskbook.ui.screens.mainScreen

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.presentation.navigation.ScreensRouter
import com.mr.anonym.riskbook.ui.components.LineChart
import com.mr.anonym.riskbook.ui.components.colorSelector

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
//    Context
    val activity = LocalActivity.current

//    Int
    val selectedTabIndex = remember { mutableStateOf( 0 ) }

//    List
    val transactions = viewModel.transactions
    val points = viewModel.points
    val labels = viewModel.labels
    val tabs = viewModel.tabs

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
                .padding(paddingValues)
        ) {
            if ( points.isNotEmpty()){
                LineChart(
                    backgroundColor = colorSelector(4),
                    points = points,
                    labels
                )
            }
            Spacer(Modifier.height(10.dp))
            MarketTabRow(
                backgroundColor = colorSelector(0),
                textColor = colorSelector(1),
                selectedTabColor = colorSelector(6),
                fontFamily = iosFont,
                selectedTabIndex = selectedTabIndex.value,
                tabs = tabs,
                onClick = {
                    selectedTabIndex.value = it
                    if ( selectedTabIndex.value == 0 ){
                        viewModel.getTransactions()
                    }else{
                        viewModel.getTransactionsByMarket(tabs[it])
                    }
                },
                content = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        items(transactions.value){ model->
                            MainScreenItem(
                                backgroundColor = colorSelector(1),
                                borderColor = if ( model.position) colorSelector(4) else colorSelector(3),
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
            )
        }
    }
}