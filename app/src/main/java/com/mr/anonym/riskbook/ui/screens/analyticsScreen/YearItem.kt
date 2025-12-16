package com.mr.anonym.riskbook.ui.screens.analyticsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YearItem(
    backgroundColor: Color,
    textColor: Color,
    fontFamily: FontFamily,
    year: Int,
    onClick:()-> Unit
) {
    Row(
        modifier = Modifier
            .width(80.dp)
            .height(30.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(15.dp))
            .padding(5.dp)
            .clickable{ onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = year.toString(),
            fontSize = 13.sp,
            color = textColor,
            fontFamily = fontFamily
        )
    }
    Spacer(Modifier.width(7.dp))
}