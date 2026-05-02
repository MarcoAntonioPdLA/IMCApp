package com.example.imcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color

val VeryLightGray = Color(0xFFF0EEF6)
val Purple = Color(0xFF2802A1)
val LightPurple = Color(0xFFD3CCFA)
val DarkGreen = Color(0xFF006702)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}