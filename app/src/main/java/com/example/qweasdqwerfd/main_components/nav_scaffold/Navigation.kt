package com.example.qweasdqwerfd.main_components.nav_scaffold

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweasdqwerfd.main_components.view_models.AuthViewModel
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel
import com.example.qweasdqwerfd.main_components.view_models.TaskViewModel
import com.example.qweasdqwerfd.screens.auth.ForgetPasswordScreen
import com.example.qweasdqwerfd.screens.auth.SignInScreen
import com.example.qweasdqwerfd.screens.auth.SignUpScreen
import com.example.qweasdqwerfd.screens.boards.AllBoardsScreen
import com.example.qweasdqwerfd.screens.boards.ProfileScreen
import com.example.qweasdqwerfd.screens.columns.ColumnScreen

@SuppressLint("NewApi")
@Composable
fun Navigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    boardViewModel: BoardViewModel,
    columnViewModel: ColumnViewModel,
    currentColumnTitle: MutableState<String>,
    taskViewModel: TaskViewModel,
    showDialog: MutableState<Boolean>,
    selectedTasks: SnapshotStateList<Long>,
    isSelectionMode: Boolean
) {
    val accessToken by authViewModel.accessToken
    val startDestination = if (!accessToken.isNullOrEmpty()) "all_tasks" else "sign_in"


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("sign_in") {
            SignInScreen(navController, authViewModel)
        }
        composable("sign_up") {
            SignUpScreen(navController, authViewModel)
        }
        composable("all_tasks") {
            AllBoardsScreen(
                boardViewModel,
                columnViewModel,
                navController,
                currentColumnTitle
            )
        }

        composable("profile") {
            ProfileScreen(navController, authViewModel)
        }
        composable("forget") {
            ForgetPasswordScreen(navController)
        }
        composable("columns") {
            ColumnScreen(
                columnViewModel,
                boardViewModel,
                taskViewModel,
                currentColumnTitle,
                navController,
                showDialog,
                selectedTasks,
                isSelectionMode
            )
        }

    }
}
