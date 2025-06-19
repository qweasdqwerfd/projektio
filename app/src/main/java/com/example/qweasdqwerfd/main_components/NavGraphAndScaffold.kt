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
import com.example.qweasdqwerfd.main_components.view_models.AuthViewModel
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.screens.auth.ForgetPasswordScreen
import com.example.qweasdqwerfd.screens.auth.SignInScreen
import com.example.qweasdqwerfd.screens.auth.SignUpScreen
import com.example.qweasdqwerfd.screens.boards.AllBoardsScreen
import com.example.qweasdqwerfd.screens.boards.CreateBoardScreen
import com.example.qweasdqwerfd.screens.boards.ProfileScreen

@Composable
fun NavGraphAndScaffold(
    authViewModel: AuthViewModel = viewModel(),
    navHostController: NavHostController,
    boardViewModel: BoardViewModel = viewModel()
) {
    val accessToken by authViewModel.accessToken
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
                        viewModel = authViewModel
                    )
                }
                composable("sign_up") {
                    SignUpScreen(
                        navGraph = navHostController,
                        viewModel = authViewModel
                    )
                }
                composable("all_tasks") {
                    AllBoardsScreen(boardViewModel)
                }
                composable("create_task") {
                    CreateBoardScreen(
                        navHostController
                    )
                }
                composable("plus") {
                    FloatActionBar(navHostController)
                }
                composable("profile") {
                    ProfileScreen(
                        navHostController,
                        authViewModel
                    )
                }
                composable("forget") {
                    ForgetPasswordScreen(navHostController)
                }

            }
        }
    }
}
