package com.mr.anonym.riskbook.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mr.anonym.riskbook.presentation.navigation.NavGraph
import com.mr.anonym.riskbook.ui.theme.RiskBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RiskBookTheme {
                NavGraph()
            }
        }
    }
}