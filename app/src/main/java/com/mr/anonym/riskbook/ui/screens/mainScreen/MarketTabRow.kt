package com.mr.anonym.riskbook.ui.screens.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarketTabRow(
    backgroundColor: Color,
    textColor: Color,
    selectedTabColor: Color,
    fontFamily: FontFamily,
    selectedTabIndex: Int,
    tabs: List<String>,
    onClick: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    if (tabs.isEmpty()) return
    SecondaryScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = backgroundColor,
        contentColor = backgroundColor,
        edgePadding = 15.dp,
        tabs = {
            tabs.forEachIndexed { index,string ->
                Tab(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selected = selectedTabIndex == index,
                    onClick = {
                        onClick(index)
                    },
                    selectedContentColor = selectedTabColor,
                    unselectedContentColor = backgroundColor
                ) {
                    Spacer(Modifier.width(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = string,
                            color = textColor,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily
                        )
                        Spacer(Modifier.width(15.dp))
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    )
    content()
}