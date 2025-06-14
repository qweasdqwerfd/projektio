package com.example.qweasdqwerfd.main_components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.qweasdqwerfd.instruments.ProjektioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val viewModel = ViewModelProvider(this)[MyViewModel::class.java]

            ProjektioTheme(darkTheme = true) {
                MainComponents(
                    viewModel,
                    this
                )

            }
        }
    }
}

