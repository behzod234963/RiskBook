package com.mr.anonym.riskbook.ui.screens.conclusionsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.data.model.TransactionsModel

@Composable
fun ConclusionsItem(
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color,
    profitColor: Color,
    fontFamily: FontFamily,
    model: TransactionsModel,
    onClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (model.position) painterResource(R.drawable.ic_trend_up) else painterResource(
                        R.drawable.ic_trend_down
                    ),
                    tint = borderColor,
                    contentDescription = ""
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = model.pair,
                    color = textColor,
                    fontSize = 16.sp,
                    fontFamily = fontFamily
                )
            }
            Text(
                text = model.profit.toString(),
                color = profitColor,
                fontSize = 16.sp,
                fontFamily = fontFamily
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Conclusion",
            fontSize = 16.sp,
            color = textColor,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(5.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = model.finalConclusion,
            fontSize = 16.sp,
            color = profitColor,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center
        )
    }
    Spacer(Modifier.height(10.dp))
}