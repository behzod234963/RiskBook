package com.mr.anonym.riskbook.ui.screens.calculatorScreen

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.presentation.managers.clipboardManager
import com.mr.anonym.riskbook.presentation.managers.riskCalculator
import com.mr.anonym.riskbook.ui.components.CircleButton
import com.mr.anonym.riskbook.ui.components.OutlinedTF
import com.mr.anonym.riskbook.ui.components.colorSelector

@Composable
fun CalculatorScreen(
    navController: NavController
) {

//    Context
    val context = LocalContext.current

//    Objects
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

//    String
    val risk = remember { mutableStateOf("") }
    val rVolume = remember { mutableStateOf("") }
    val entry = remember { mutableStateOf("") }
    val stopLoss = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf(0.0) }

//    State
    val saibaFont = FontFamily(Font(R.font.saiba))
    val iosFont = FontFamily(Font(R.font.ios_font))
    val refreshButtonInteractionSource = remember { MutableInteractionSource() }
    val isPressed by refreshButtonInteractionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) 0.80f else 1f)

    Scaffold(
        containerColor = colorSelector(0),
        contentColor = colorSelector(0),
        topBar = {
            CalculatorTopBar(
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
            Row {
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = risk.value,
                    onValueChange = { risk.value = it },
                    label = "Risk %",
                    onTrailingClick = { }
                )
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = rVolume.value,
                    onValueChange = { rVolume.value = it },
                    label = "Risk Volume",
                    onTrailingClick = { }
                )
            }
            Row {
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = entry.value,
                    onValueChange = { entry.value = it },
                    label = "Entry price",
                    onTrailingClick = { }
                )
                OutlinedTF(
                    modifier = Modifier
                        .fillMaxWidth(),
                    containerColor = colorSelector(0),
                    textColor = colorSelector(1),
                    borderColor = colorSelector(1),
                    fontFamily = iosFont,
                    isNumberField = true,
                    showTrailingIcon = false,
                    isSingleLine = true,
                    value = stopLoss.value,
                    onValueChange = { stopLoss.value = it },
                    label = "Stop-loss",
                    onTrailingClick = { }
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(color = colorSelector(4), width = 1.dp, shape = RoundedCornerShape(15.dp))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Amount: ${amount.value}",
                    color = colorSelector(4),
                    fontSize = 22.sp,
                    fontFamily = iosFont
                )
                IconButton(
                    onClick = {
                        clipboardManager(
                            text = amount.value,
                            manager = clipboardManager
                        )
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        tint = colorSelector(4),
                        contentDescription = "Copy to clipboard"
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CircleButton(
                    buttonColor = colorSelector(4),
                    interactionSource = refreshButtonInteractionSource,
                    scale = scale,
                    onClick = {
                        if (
                            risk.value.isNotEmpty() &&
                            entry.value.isNotEmpty() &&
                            stopLoss.value.isNotEmpty()
                        ) {
                            amount.value = riskCalculator(
                                entry.value.toDouble(),
                                stopLoss.value.toDouble(),
                                volume = rVolume.value.toInt()
                            )
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.ic_refresh),
                            tint = colorSelector(1),
                            contentDescription = ""
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCalculatorScreen() {
    CalculatorScreen(NavController(LocalContext.current))
}