package com.example.qweasdqwerfd.main_components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.qweasdqwerfd.instruments.ProjektioTheme
import com.example.qweasdqwerfd.main_components.view_models.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            )[AuthViewModel::class.java]


            val navController = rememberNavController()

            ProjektioTheme(darkTheme = true) {
                NavGraphAndScaffold(
                    authViewModel = viewModel,
                    navHostController = navController,
                )
            }
        }
    }
}

