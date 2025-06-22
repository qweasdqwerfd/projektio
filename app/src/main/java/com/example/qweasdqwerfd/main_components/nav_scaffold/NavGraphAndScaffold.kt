package com.example.qweasdqwerfd.main_components.nav_scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.main_components.top_bar.TopBar
import com.example.qweasdqwerfd.main_components.view_models.AuthViewModel
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel
import com.example.qweasdqwerfd.custom_components.board.CreateBoardDialog
import com.example.qweasdqwerfd.custom_components.tasks_column.CreateColumnDialog

@Composable
fun NavGraphAndScaffold(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = viewModel(),
    boardViewModel: BoardViewModel = viewModel(),
    columnViewModel: ColumnViewModel = viewModel()
) {
    val route = currentRoute(navHostController).value
    val showDialog = remember { mutableStateOf(false) }

    val currentColumnTitle = remember { mutableStateOf("Колонка") }

    Scaffold(
        topBar = {
            if (route != "sign_in" && route.isNotEmpty()) {
                TopBar(
                    navHostController,
                    route,
                    currentColumnTitle.value
                )
            }
        },
        floatingActionButton = {
            if (route == "all_tasks") {
                FloatActionBar {
                    showDialog.value = true
                }
            }
            if (route == "columns") {
                FloatActionBar {
                    showDialog.value = true
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(
                navController = navHostController,
                authViewModel = authViewModel,
                boardViewModel = boardViewModel,
                columnViewModel = columnViewModel,
                currentColumnTitle
            )

            if (showDialog.value && route == "all_tasks") {
                CreateBoardDialog(
                    navHostController = navHostController,
                    onDismiss = { showDialog.value = false }
                )
            }
            if (showDialog.value && route == "columns") {
                CreateColumnDialog(
                    navController = navHostController,
                    onDismiss = { showDialog.value = false },
                    boardViewModel,
                    columnViewModel
                )
            }
        }
    }
}