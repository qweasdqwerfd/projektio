package com.example.qweasdqwerfd.help_const

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun currentRoute(navController: NavHostController): State<String> {
    val currentRoute = remember { mutableStateOf("") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute.value = backStackEntry.destination.route ?: ""
        }
    }
    return currentRoute
}
