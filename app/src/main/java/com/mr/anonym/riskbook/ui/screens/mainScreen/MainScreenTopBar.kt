package com.mr.anonym.riskbook.ui.screens.mainScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.riskbook.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    containerColor: Color,
    textColor: Color,
    fontFamily: FontFamily,
    title: String,
    onRulesClick:()-> Unit,
    onCalculatorClick:()-> Unit,
    onAnalyticsScreen:()->Unit,
    onAddClick:()-> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors( containerColor = containerColor ),
        title = {
            Text(
                text = title,
                fontSize = 22.sp,
                color = textColor,
                fontFamily = fontFamily
            )
        },
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onCalculatorClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_calculator),
                        tint = textColor,
                        contentDescription = "Risk calculator"
                    )
                }
                IconButton(
                    onClick = { onRulesClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_rules),
                        tint = textColor,
                        contentDescription = "go ro rules"
                    )
                }
                IconButton(
                    onClick = { onAnalyticsScreen() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_chart_histogram),
                        tint = textColor,
                        contentDescription = "Monitoring"
                    )
                }
                Spacer(Modifier.width(10.dp))
                IconButton(
                    onClick = { onAddClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_transaction),
                        tint = textColor,
                        contentDescription = "Add transaction"
                    )
                }
            }
        }
    )
}