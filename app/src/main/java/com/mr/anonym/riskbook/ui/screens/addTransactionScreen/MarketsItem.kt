package com.mr.anonym.riskbook.ui.screens.addTransactionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun MarketsItem(
    backgroundColor: Color,
    textColor: Color,
    fontFamily: FontFamily,
    model: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = model,
            fontSize = 13.sp,
            color = textColor,
            fontFamily = fontFamily
        )
    }
    Spacer(Modifier.width(7.dp))
}