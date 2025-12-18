package com.mr.anonym.riskbook.ui.screens.calculatorScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.mr.anonym.riskbook.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTopBar(
    backgroundColor: Color,
    textColor: Color,
    fontFamily: FontFamily,
    onBackClick:()-> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        ),
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    tint = textColor,
                    contentDescription = "back to main"
                )
            }
        },
        title = {
            Text(
                text = "Risk calculator",
                fontSize = 22.sp,
                color = textColor,
                fontFamily = fontFamily
            )
        }
    )
}