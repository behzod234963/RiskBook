package com.mr.anonym.riskbook.presentation.managers

import android.content.ClipData
import android.content.ClipboardManager

fun clipboardManager(text: Double,manager: ClipboardManager){
    val clip = ClipData.newPlainText("Amount",text.toString().take(4))
    manager.setPrimaryClip(clip)
}