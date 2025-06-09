package com.example.qweasdqwerfd.main_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.screens.auth.SignInScreen
import com.example.qweasdqwerfd.screens.auth.SignUpScreen

@Composable
fun MainComponents(viewModel: MyViewModel) {
    val navController = rememberNavController()

    var currentRoute by remember { mutableStateOf("") }

    SignUpScreen(
        viewModel = viewModel,
        navGraph = navController
    )

    SignInScreen(
        navHostController = navController,
        viewModel = viewModel
    )

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute = backStackEntry.destination.route ?: "Ресурсы"
        }
    }


    Scaffold(
        topBar = { TopBar(navController, currentRoute) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (currentRoute == "all_tasks")
                FloatActionBar(navController)
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                viewModel,
                navController
            )
        }

    }
}