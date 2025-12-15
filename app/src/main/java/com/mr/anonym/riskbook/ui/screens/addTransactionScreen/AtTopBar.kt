package com.mr.anonym.riskbook.ui.screens.addTransactionScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.mr.anonym.riskbook.R
import com.mr.anonym.riskbook.ui.components.CircleButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtTopBar(
    containerColor: Color,
    buttonColor: Color,
    textColor: Color,
    fontFamily: FontFamily,
    title: String,
    onBackClick:()-> Unit,
    onSaveClick:()-> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState( if ( isPressed ) 0.90f else 1f )
    TopAppBar(
        title = {
            Text(
                text = title,
                color = textColor,
                fontSize = 22.sp,
                fontFamily = fontFamily
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    tint = textColor,
                    contentDescription = "Back to main"
                )
            }
        },
        actions = {
            CircleButton(
                buttonColor = buttonColor,
                interactionSource = interactionSource,
                scale = scale,
                onClick = {
                    onSaveClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_save),
                    contentDescription = "Save transaction",
                    tint = textColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
    )
}