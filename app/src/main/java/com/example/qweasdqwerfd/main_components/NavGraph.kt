package com.example.qweasdqwerfd.main_components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweasdqwerfd.screens.SignIn
import com.example.qweasdqwerfd.screens.SignUp

@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = "sign_in"
    ) {
        composable("sign_in") {
            SignIn(navHostController)
        }
        composable("sign_up") {
            SignUp()
        }
    }
}