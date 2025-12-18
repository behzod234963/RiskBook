package com.mr.anonym.riskbook.ui.screens.conclusionsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.presentation.navigation.ScreensRouter
import com.mr.anonym.riskbook.ui.components.colorSelector

@Composable
fun ConclusionsScreen(
    navController: NavController,
    viewModel: ConclusionViewModel = hiltViewModel()
) {

//    List
    val transactions = viewModel.transactions

//    Font
    val saibaFont = FontFamily(Font(R.font.saiba))
    val iosFont = FontFamily(Font(R.font.ios_font))

    Scaffold(
        containerColor = colorSelector(0),
        contentColor = colorSelector(0),
        topBar = {
            ConclusionsTopBar(
                backgroundColor = colorSelector(4),
                textColor = colorSelector(1),
                fontFamily = saibaFont,
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
            LazyColumn {
                items(transactions.value){ model->
                    ConclusionsItem(
                        backgroundColor = colorSelector(1),
                        borderColor = if ( model.position) colorSelector(4) else colorSelector(3),
                        textColor = colorSelector(0),
                        profitColor = if (model.profit.toString().startsWith('-')) colorSelector(3)
                        else colorSelector(4),
                        fontFamily = iosFont,
                        model = model
                    ) {
                        navController.navigate(ScreensRouter.AddTransactionScreen.route + "/${model.id}")
                    }
                }
            }
        }
    }
}