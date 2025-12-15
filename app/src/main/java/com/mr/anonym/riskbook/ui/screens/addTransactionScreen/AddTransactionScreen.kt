package com.mr.anonym.riskbook.ui.screens.addTransactionScreen

import android.content.ClipboardManager
import android.content.Context
import android.icu.util.Calendar
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.presentation.managers.clipboardManager
import com.mr.anonym.riskbook.presentation.managers.riskCalculator
import com.mr.anonym.riskbook.ui.components.CircleButton
import com.mr.anonym.riskbook.ui.components.ColorSelector
import com.mr.anonym.riskbook.ui.components.OutlinedTF
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AddTransactionScreen(
    id: Int,
    navController: NavController,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {

//    Context
    val context = LocalContext.current

//    Objects
    val calendarInstance = Calendar.getInstance()
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

//    Booleans
    val isAmountCalculated = remember { mutableStateOf( false ) }

//    String
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

//    Font
    val saibaFont = FontFamily(Font(R.font.saiba))
    val iosFont = FontFamily(Font(R.font.ios_font))

//    State
    val verticalScrollState = rememberScrollState()
    val refreshButtonInteractionSource = remember { MutableInteractionSource() }
    val isPressed by refreshButtonInteractionSource.collectIsPressedAsState()
    val scale by animateFloatAsState( if ( isPressed ) 0.80f else 1f )

//    UI
    Scaffold(
        containerColor = ColorSelector(0),
        contentColor = ColorSelector(0),
        topBar = {
            AtTopBar(
                containerColor = ColorSelector(4),
                buttonColor = ColorSelector(5),
                textColor = ColorSelector(1),
                fontFamily = saibaFont,
                title = pairValue.value,
                onBackClick = { navController.navigateUp() },
                onSaveClick = {
                    if ( id == -1 ){
                        if ( isAmountCalculated.value ){
                            if (pairValue.value.isNotEmpty()){
                                val factory = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                                val currentDate = factory.format(calendarInstance.time)
                                viewModel.insertTransaction(TransactionsModel(
                                    pair = pairValue.value,
                                    riskPercent = riskPercentValue.value,
                                    riskVolume = riskVolumeValue.value.toInt(),
                                    entryPrice = entryValue.value.toDouble(),
                                    stopLoss = stopLossValue.value.toDouble(),
                                    margin = marginValue.value.toDouble(),
                                    leverage = leverageValue.value,
                                    amount = amountValue.value,
                                    commentForEntry = commentForEntryValue.value,
                                    commentForTakeProfit = commentForTakeProfitValue.value,
                                    finalConclusion = finalConclusionValue.value,
                                    profit = if ( profitValue.value.isNotEmpty() ) profitValue.value.toDouble() else 0.0,
                                    dateAdded = currentDate,
                                    position = position.value,
                                    takeProfit = if ( takeProfitValue.value.isNotEmpty() ) takeProfitValue.value.toDouble() else 0.0,
                                    month = month,
                                    year = year
                                ))
                                navController.navigateUp()
                            }
                        }
                    }else{
                        if ( pairValue.value.isNotEmpty() ){
                            val month = calendarInstance.get(Calendar.MONTH)
                            viewModel.insertTransaction(TransactionsModel(
                                id = modelID.value,
                                pair = pairValue.value,
                                riskPercent = riskPercentValue.value,
                                riskVolume = riskVolumeValue.value.toInt(),
                                entryPrice = entryValue.value.toDouble(),
                                stopLoss = stopLossValue.value.toDouble(),
                                margin = marginValue.value.toDouble(),
                                leverage = marginValue.value,
                                amount = amountValue.value,
                                commentForEntry = commentForEntryValue.value,
                                commentForTakeProfit = commentForTakeProfitValue.value,
                                finalConclusion = finalConclusionValue.value,
                                profit = if ( profitValue.value.isNotEmpty() ) profitValue.value.toDouble() else 0.0,
                                dateAdded = dateAdded.value,
                                position = position.value,
                                takeProfit = if ( takeProfitValue.value.isNotEmpty() ) takeProfitValue.value.toDouble() else 0.0,
                                month = month,
                                year = year
                            ))
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
//            Transaction pair field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                containerColor = ColorSelector(0),
                textColor = ColorSelector(1),
                borderColor = ColorSelector(1),
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
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1),
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
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1),
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
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1),
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
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = true,
                    isSingleLine = true,
                    value = stopLossValue.value,
                    onValueChange = { viewModel.changeStopLossValue(it)},
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
                        text = "Position: ${if ( position.value ) "Long" else "Short"}",
                        color = ColorSelector(1),
                        fontSize = 14.sp,
                        fontFamily = iosFont
                    )
                    Switch(
                        checked = position.value,
                        onCheckedChange = {
                            viewModel.changePosition(it)
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = ColorSelector(4),
                            uncheckedTrackColor = ColorSelector(3),
                            checkedThumbColor = ColorSelector(0),
                            uncheckedThumbColor = ColorSelector(0),
                            checkedBorderColor = ColorSelector(0),
                            uncheckedBorderColor = ColorSelector(0)
                        ),
                    )
                }
//                Take-profit field
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1  ),
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
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1),
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
                    containerColor = ColorSelector(0),
                    textColor = ColorSelector(1),
                    borderColor = ColorSelector(1  ),
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
                color = ColorSelector(4),
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//                Risk
            Text(
                text = "Risk: ${riskPercentValue.value} %",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Risk volume
            Text(
                text = "Risk volume: ${riskVolumeValue.value} USDT",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//            Position
            Text(
                text = "Position: ${if ( position.value ) "Long" else "Short"}",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Entry
            Text(
                text = "Entry price: ${entryValue.value}",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Stop-loss
            Text(
                text = "Stop-loss: ${stopLossValue.value}",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//            Take-profit
            Text(
                text = "Take-profit: ${takeProfitValue.value}",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//                Margin
            Text(
                text = "Margin: ${marginValue.value} USDT",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
//                Leverage
            Text(
                text = "Leverage: ${leverageValue.value} x",
                color = ColorSelector(4),
                fontSize = 16.sp,
                fontFamily = iosFont
            )
            Spacer(Modifier.height(15.dp))
//            Amount
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(width = 0.5.dp,color = ColorSelector(6),shape = RoundedCornerShape(15.dp))
                    .background(color = ColorSelector(0), shape = RoundedCornerShape(15.dp))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Amount: ${amountValue.value.toString().take(8)}",
                    color = ColorSelector(4),
                    fontSize = 22.sp,
                    fontFamily = iosFont
                )
                IconButton(
                    onClick = {
                        clipboardManager(text = amountValue.value, manager = clipboardManager)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        tint = ColorSelector(4),
                        contentDescription = "Copy to clipboard"
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircleButton(
                    buttonColor = ColorSelector(4),
                    interactionSource = refreshButtonInteractionSource,
                    scale = scale,
                    onClick = { if (
                        riskVolumeValue.value.isNotEmpty() &&
                        entryValue.value.isNotEmpty() &&
                        stopLossValue.value.isNotEmpty()
                    ){
                        viewModel.changeAmountValue(
                            riskCalculator(
                                entryValue.value.toDouble(),
                                stopLossValue.value.toDouble(),
                                volume = riskVolumeValue.value.toInt()
                            )
                        )
                        isAmountCalculated.value = true
                    } },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.ic_refresh),
                            tint = ColorSelector(1),
                            contentDescription = ""
                        )
                    }
                )
            }
            Spacer(Modifier.height(10.dp))
//            Comment for entry field
            OutlinedTF(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = ColorSelector(0),
                textColor = ColorSelector(1),
                borderColor = ColorSelector(2),
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
                containerColor = ColorSelector(0),
                textColor = ColorSelector(1),
                borderColor = ColorSelector(2),
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
                containerColor = ColorSelector(0),
                textColor = ColorSelector(1),
                borderColor = ColorSelector(2),
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
                containerColor = ColorSelector(0),
                textColor = ColorSelector(1),
                borderColor = ColorSelector(2),
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