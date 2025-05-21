package com.example.qweasdqwerfd.main_components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.qweasdqwerfd.instruments.ProjektioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjektioTheme(darkTheme = true) {
                MainScreen()
            }
        }
    }
}

