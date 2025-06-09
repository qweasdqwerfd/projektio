package com.example.qweasdqwerfd.main_components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.screens.tasks.AllTasksScreen
import com.example.qweasdqwerfd.screens.tasks.CreateTaskScreen
import com.example.qweasdqwerfd.screens.auth.ForgetPasswordScreen
import com.example.qweasdqwerfd.screens.tasks.ProfileScreen
import com.example.qweasdqwerfd.screens.auth.SignInScreen
import com.example.qweasdqwerfd.screens.auth.SignUpScreen

@Composable
fun NavGraph(
    viewModel: MyViewModel = viewModel(),
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = "sign_in"
    ) {
        composable("sign_in") {
            SignInScreen(navHostController, viewModel)
        }
        composable("sign_up") {
            SignUpScreen(
                navGraph = navHostController,
                viewModel = viewModel
            )
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