package com.example.qweasdqwerfd.main_components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.qweasdqwerfd.instruments.ProjektioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            )[MyViewModel::class.java]

            val navController = rememberNavController()

            ProjektioTheme(darkTheme = true) {
                NavGraph(
                    viewModel = viewModel,
                    navHostController = navController,
                )
            }
        }
    }
}

