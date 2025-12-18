package com.mr.anonym.riskbook.ui.screens.addTransactionScreen

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.presentation.managers.riskCalculator
import com.mr.anonym.riskbook.ui.components.OutlinedTF
import com.mr.anonym.riskbook.ui.components.colorSelector
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AddTransactionScreen(
    id: Int,
    navController: NavController,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {

//    Objects
    val calendarInstance = Calendar.getInstance()

//    String
    val marketValue = viewModel.marketValue
    val pairValue = viewModel.pairValue
    val riskPercentValue = viewModel.riskPercentValue
    val riskVolumeValue = viewModel.riskVolumeValue
    val entryValue = viewModel.entryValue
    val stopLossValue = viewModel.stopLossValue
    val marginValue = viewModel.marginValue
    val leverageValue = viewModel.leverageValue
    val commentForEntryValue = viewModel.commentForEntryValue
    val commentForTakeProfitValue = viewModel.commentForTakeValue
    val finalConclusionValue = viewModel.finalConclusionValue
    val profitValue = viewModel.profitValue
    val amountValue = viewModel.amountValue
    val dateAdded = viewModel.dateAdded
    val position = viewModel.position
    val takeProfitValue = viewModel.takeProfitValue

//    Int
    val modelID = viewModel.id
    val month = calendarInstance.get(Calendar.MONTH)
    val year = calendarInstance.get(Calendar.YEAR)

//    List
    val markets = viewModel.markets

//    Font
    val saibaFont = FontFamily(Font(R.font.saiba))
    val iosFont = FontFamily(Font(R.font.ios_font))

//    State
    val verticalScrollState = rememberScrollState()

//    UI
    Scaffold(
        containerColor = colorSelector(0),
        contentColor = colorSelector(0),
        topBar = {
            AtTopBar(
                containerColor = colorSelector(4),
                buttonColor = colorSelector(5),
                textColor = colorSelector(1),
                fontFamily = saibaFont,
                title = pairValue.value,
                onBackClick = { navController.navigateUp() },
                onSaveClick = {
                    if (id == -1) {
                        if (
                            pairValue.value.isNotEmpty() &&
                            marketValue.value.isNotEmpty()
                        ) {
                            viewModel.changeAmountValue(
                                riskCalculator(
                                    entry = entryValue.value.toDouble(),
                                    stopLoss = stopLossValue.value.toDouble(),
                                    volume = riskVolumeValue.value.toInt()
                                )
                            )
                            val factory =
                                SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                            val currentDate = factory.format(calendarInstance.time)
                            viewModel.insertTransaction(
                                TransactionsModel(
                                    market = marketValue.value,
                                    pair = pairValue.value,
                                    riskPercent = riskPercentValue.value,
                                    riskVolume = if (riskVolumeValue.value.isNotEmpty()) riskVolumeValue.value.toInt() else 0,
                                    entryPrice = if (entryValue.value.isNotEmpty()) entryValue.value.toDouble() else 0.0,
                                    stopLoss = if (stopLossValue.value.isNotEmpty()) stopLossValue.value.toDouble() else 0.0,
                                    takeProfit = if (takeProfitValue.value.isNotEmpty()) takeProfitValue.value.toDouble() else 0.0,
                                    position = position.value,
                                    margin = if (marginValue.value.isNotEmpty()) marginValue.value.toDouble() else 0.0,
                                    leverage = leverageValue.value,
                                    amount = amountValue.value,
                                    commentForEntry = commentForEntryValue.value,
                                    commentForTakeProfit = commentForTakeProfitValue.value,
                                    finalConclusion = finalConclusionValue.value,
                                    profit = if (profitValue.value.isNotEmpty()) profitValue.value.toDouble() else 0.0,
                                    dateAdded = currentDate,
                                    month = month,
                                    year = year
                                )
                            )
                            navController.navigateUp()
                        }
                    } else {
                        if (
                            pairValue.value.isNotEmpty() &&
                            marketValue.value.isNotEmpty()
                        ) {
                            viewModel.changeAmountValue(
                                riskCalculator(
                                    entry = entryValue.value.toDouble(),
                                    stopLoss = stopLossValue.value.toDouble(),
                                    volume = riskVolumeValue.value.toInt()
                                )
                            )
                            val month = calendarInstance.get(Calendar.MONTH)
                            viewModel.insertTransaction(
                                TransactionsModel(
                                    id = modelID.value,
                                    market = marketValue.value,
                                    pair = pairValue.value,
                                    riskPercent = riskPercentValue.value,
                                    riskVolume = if (riskVolumeValue.value.isNotEmpty()) riskVolumeValue.value.toInt() else 0,
                                    entryPrice = if (entryValue.value.isNotEmpty()) entryValue.value.toDouble() else 0.0,
                                    stopLoss = if (stopLossValue.value.isNotEmpty()) stopLossValue.value.toDouble() else 0.0,
                                    takeProfit = if (takeProfitValue.value.isNotEmpty()) takeProfitValue.value.toDouble() else 0.0,
                                    position = position.value,
                                    margin = if (marginValue.value.isNotEmpty()) marginValue.value.toDouble() else 0.0,
                                    leverage = leverageValue.value,
                                    amount = amountValue.value,
                                    commentForEntry = commentForEntryValue.value,
                                    commentForTakeProfit = commentForTakeProfitValue.value,
                                    finalConclusion = finalConclusionValue.value,
                                    profit = if (profitValue.value.isNotEmpty()) profitValue.value.toDouble() else 0.0,
                                    dateAdded = dateAdded.value,
                                    month = month,
                                    year = year
                                )
                            )
                            navController.navigateUp()
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
                .verticalScroll(verticalScrollState)
                .imePadding()
        ) {
            LazyRow {
                items(markets.value){ model->
                    MarketsItem(
                        backgroundColor = colorSelector(6),
                        textColor = colorSelector(1),
                        fontFamily = iosFont,
                        model = model,
                        onClick = {
                            viewModel.changeMarketValue(model)
                        }
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
//            Market field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                containerColor = colorSelector(0),
                textColor = colorSelector(1),
                borderColor = colorSelector(1),
                fontFamily = iosFont,
                isNumberField = false,
                showTrailingIcon = true,
                isSingleLine = true,
                value = marketValue.value,
                onValueChange = { viewModel.changeMarketValue(it) },
                label = "Market",
                onTrailingClick = { viewModel.changeMarketValue("") }
            )
            Spacer(Modifier.height(15.dp))
//            Transaction pair field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                containerColor = colorSelector(0),
                textColor = colorSelector(1),
                borderColor = colorSelector(1),
                fontFamily = iosFont,
                isNumberField = false,
                showTrailingIcon = true,
                isSingleLine = true,
                value = pairValue.value,
                onValueChange = {
                    viewModel.changePairValue(it)
                },
                label = "Transaction Pair"
            ) {
                viewModel.changePairValue("")
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
//                Risk percent field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = riskPercentValue.value,
                    onValueChange = { viewModel.changeRiskPercentValue(it) },
                    label = "Risk %"
                ) { }
//                Risk volume field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = riskVolumeValue.value,
                    onValueChange = { viewModel.changeRiskVolumeValue(it) },
                    label = "Volume USDT"
                ) { }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
//                Entry field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = true,
                    isSingleLine = true,
                    value = entryValue.value,
                    onValueChange = { viewModel.changeEntryValue(it) },
                    label = "Entry"
                ) { viewModel.changeEntryValue("") }
//                Stop-loss field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = true,
                    isSingleLine = true,
                    value = stopLossValue.value,
                    onValueChange = { viewModel.changeStopLossValue(it) },
                    label = "Stop-loss"
                ) { viewModel.changeStopLossValue("") }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                ) {
//                Position field
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Position: ${if (position.value) "Long" else "Short"}",
                        color = colorSelector(1),
                        fontSize = 14.sp,
                        fontFamily = iosFont
                    )
                    Switch(
                        checked = position.value,
                        onCheckedChange = {
                            viewModel.changePosition(it)
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = colorSelector(4),
                            uncheckedTrackColor = colorSelector(3),
                            checkedThumbColor = colorSelector(0),
                            uncheckedThumbColor = colorSelector(0),
                            checkedBorderColor = colorSelector(0),
                            uncheckedBorderColor = colorSelector(0)
                        ),
                    )
                }
//                Take-profit field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = true,
                    isSingleLine = true,
                    value = takeProfitValue.value,
                    onValueChange = { viewModel.changeTakeProfitValue(it) },
                    label = "Take-profit"
                ) { viewModel.changeTakeProfitValue("") }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
//                Margin field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = true,
                    isSingleLine = true,
                    value = marginValue.value,
                    onValueChange = { viewModel.changeMarginValue(it) },
                    label = "Margin"
                ) { viewModel.changeMarginValue("") }
//                Leverage
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = leverageValue.value,
                    onValueChange = { viewModel.changeLeverageValue(it) },
                    label = "Leverage x"
                ) { viewModel.changeLeverageValue("") }
            }
            Spacer(Modifier.height(15.dp))
//            Pair
            Text(
                text = pairValue.value,
                fontSize = 16.sp,
                color = colorSelector(4),
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//                Risk
            Text(
                text = "Risk: ${riskPercentValue.value} %",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Risk volume
            Text(
                text = "Risk volume: ${riskVolumeValue.value} USDT",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//            Position
            Text(
                text = "Position: ${if (position.value) "Long" else "Short"}",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Entry
            Text(
                text = "Entry price: ${entryValue.value}",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Stop-loss
            Text(
                text = "Stop-loss: ${stopLossValue.value}",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//            Take-profit
            Text(
                text = "Take-profit: ${takeProfitValue.value}",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//                Margin
            Text(
                text = "Margin: ${marginValue.value} USDT",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Leverage
            Text(
                text = "Leverage: ${leverageValue.value} x",
                color = colorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//            Comment for entry field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = colorSelector(0),
                textColor = colorSelector(1),
                borderColor = colorSelector(2),
                fontFamily = iosFont,
                isNumberField = false,
                showTrailingIcon = true,
                isSingleLine = false,
                value = commentForEntryValue.value,
                onValueChange = { viewModel.changeCommentForEntryValue(it) },
                label = "Comment for entry"
            ) { viewModel.changeCommentForEntryValue("") }
            Spacer(Modifier.height(10.dp))
//            Comment for take-profit field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = colorSelector(0),
                textColor = colorSelector(1),
                borderColor = colorSelector(2),
                fontFamily = iosFont,
                isNumberField = false,
                showTrailingIcon = true,
                isSingleLine = false,
                value = commentForTakeProfitValue.value,
                onValueChange = { viewModel.changeCommentForTakeValue(it) },
                label = "Comment for take-profit"
            ) { viewModel.changeCommentForTakeValue("") }
            Spacer(Modifier.height(10.dp))
//            Final conclusion field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = colorSelector(0),
                textColor = colorSelector(1),
                borderColor = colorSelector(2),
                fontFamily = iosFont,
                isNumberField = false,
                showTrailingIcon = true,
                isSingleLine = false,
                value = finalConclusionValue.value,
                onValueChange = { viewModel.changeFinalConclusionValue(it) },
                label = "Final conclusion"
            ) { viewModel.changeFinalConclusionValue("") }
//            Profit
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = colorSelector(0),
                textColor = colorSelector(1),
                borderColor = colorSelector(2),
                fontFamily = iosFont,
                isNumberField = true,
                showTrailingIcon = true,
                isSingleLine = true,
                value = profitValue.value,
                onValueChange = { viewModel.changeProfitValue(it) },
                label = "Profit"
            ) { viewModel.changeProfitValue("") }
        }
    }
}