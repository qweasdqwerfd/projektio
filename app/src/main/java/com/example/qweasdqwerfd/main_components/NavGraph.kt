package com.example.qweasdqwerfd.main_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.help_const.currentRoute
import com.example.qweasdqwerfd.screens.auth.ForgetPasswordScreen
import com.example.qweasdqwerfd.screens.auth.SignInScreen
import com.example.qweasdqwerfd.screens.auth.SignUpScreen
import com.example.qweasdqwerfd.screens.tasks.AllTasksScreen
import com.example.qweasdqwerfd.screens.tasks.CreateTaskScreen
import com.example.qweasdqwerfd.screens.tasks.ProfileScreen

@Composable
fun NavGraph(
    viewModel: MyViewModel = viewModel(),
    navHostController: NavHostController,
) {
    val accessToken by viewModel.accessToken
    val startDestination = if (!accessToken.isNullOrEmpty()) "all_tasks" else "sign_in"
    val routeState = currentRoute(navHostController)
    val route = routeState.value

    Scaffold(
        topBar = {
            if (route != "sign_in" && route.isNotEmpty()) {
                TopBar(navHostController, route)
            }
        },
        floatingActionButton = {
            if (route == "all_tasks") {
                FloatActionBar(navHostController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center


    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navHostController,
                startDestination = startDestination
            ) {
                composable("sign_in") {
                    SignInScreen(
                        navHostController = navHostController,
                        viewModel = viewModel
                    )
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
                    ProfileScreen(
                        navHostController,
                        viewModel
                    )
                }
                composable("forget") {
                    ForgetPasswordScreen(navHostController)
                }
            }
        }
    }
}
