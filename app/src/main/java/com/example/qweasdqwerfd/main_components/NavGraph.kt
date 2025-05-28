package com.example.qweasdqwerfd.main_components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.screens.AllTasksScreen
import com.example.qweasdqwerfd.screens.CreateTaskScreen
import com.example.qweasdqwerfd.screens.ForgetPasswordScreen
import com.example.qweasdqwerfd.screens.ProfileScreen
import com.example.qweasdqwerfd.screens.SignInScreen
import com.example.qweasdqwerfd.screens.SignUpScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = "sign_in"
    ) {
        composable("sign_in") {
            SignInScreen(navHostController)
        }
        composable("sign_up") {
            SignUpScreen()
        }
        composable("all_tasks") {
            AllTasksScreen()
        }
        composable("create_task") {
            CreateTaskScreen(navHostController)
        }
        composable("plus") {
            FloatActionBar(navHostController)
        }
        composable("profile") {
            ProfileScreen(navHostController)
        }
        composable("forget") {
            ForgetPasswordScreen(navHostController)
        }
    }
}