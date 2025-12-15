package com.mr.anonym.riskbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    buttonColor: Color,
    interactionSource: MutableInteractionSource,
    scale: Float,
    onClick:()-> Unit,
    content: @Composable ()-> Unit
) {
    Box(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                onClick = onClick
            )
            .background(buttonColor, CircleShape)
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ){
        content()
    }
}