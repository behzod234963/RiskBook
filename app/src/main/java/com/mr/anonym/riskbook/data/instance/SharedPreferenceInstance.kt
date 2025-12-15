package com.mr.anonym.riskbook.data.instance

import android.content.Context
import androidx.core.content.edit

class SharedPreferenceInstance(private val context: Context) {

    private val sharedPrefs = context.getSharedPreferences("RiskAppSharedPref", Context.MODE_PRIVATE)

    fun isSystemTheme(state: Boolean){
        sharedPrefs.edit{ putBoolean("SystemTheme",state) }
    }
    fun systemThemeState(): Boolean =
        sharedPrefs.getBoolean("SystemTheme",true)

    fun isDarkTheme(state: Boolean){
        sharedPrefs.edit { putBoolean("DarkTheme",state) }
    }
    fun darkThemeState(): Boolean = sharedPrefs.getBoolean("DarkTheme",true)
}