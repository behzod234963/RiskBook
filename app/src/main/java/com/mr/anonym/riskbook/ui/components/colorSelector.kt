package com.mr.anonym.riskbook.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.mr.anonym.riskbook.data.instance.SharedPreferenceInstance

@Composable
fun colorSelector(index: Int): Color {

//    Context
    val context = LocalContext.current

//    Objects
    val sharedPrefs = SharedPreferenceInstance(context)

    val isSystemTheme = sharedPrefs.systemThemeState()
    val isDarkTheme = sharedPrefs.darkThemeState()

//    colorSelector
    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White // 0 //
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black // 1 //
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray // 2 //
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4) // 6 //

    return when(index){
//        BackgroundColor (Primary)
        0->{
            when {
                isSystemTheme -> {
                    systemPrimaryColor
                }
                isDarkTheme -> Color.Black
                else -> Color.White
            }
        }
//        TextColor (Secondary)
        1->{
            when {
                isSystemTheme -> systemSecondaryColor
                isDarkTheme -> Color.White
                else -> Color.Black
            }
        }
//        Tertiary
        2->{
            when {
                isSystemTheme -> systemTertiaryColor
                isDarkTheme -> Color.DarkGray
                else -> Color.LightGray
            }
        }
//        Red
        3->{
            Color.Red
        }
//        Green
        4->{
            Color(101, 163, 119, 255)
        }
//        Blue
        5->{
            Color(67, 123, 205, 255)
        }
//        Gray
        6->{
            when{
                isSystemTheme -> systemNineColor
                isDarkTheme -> Color(0xFF222327)
                else -> Color(0xFFF1F2F4)
            }
        }
        else -> {
            when {
                isSystemTheme -> {
                    systemPrimaryColor
                }
                isDarkTheme -> Color.Black
                else -> Color.White
            }
        }
    }
}