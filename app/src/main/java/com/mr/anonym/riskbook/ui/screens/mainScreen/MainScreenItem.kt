package com.mr.anonym.riskbook.ui.screens.mainScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.data.model.TransactionsModel
import com.mr.anonym.riskbook.ui.components.CircleButton

@Composable
fun MainScreenItem(
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color,
    profitColor: Color,
    fontFamily: FontFamily,
    model: TransactionsModel,
    onClick:()-> Unit,
    onDeleteClick:()-> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState( if ( isPressed ) 0.80f else 1f )
    Row(
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
            .clickable{ onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = if ( model.position) painterResource(R.drawable.ic_trend_up) else painterResource(R.drawable.ic_trend_down),
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
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.profit.toString(),
                color = profitColor,
                fontSize = 16.sp,
                fontFamily = fontFamily
            )
            Spacer(Modifier.width(10.dp))
            CircleButton(
                buttonColor = borderColor,
                interactionSource = interactionSource,
                scale = scale,
                onClick = { onDeleteClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    tint = textColor,
                    contentDescription = "delete transaction"
                )
            }
        }
    }
}