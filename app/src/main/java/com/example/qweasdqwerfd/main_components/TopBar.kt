package com.example.qweasdqwerfd.main_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.example.qweasdqwerfd.custom_components.top_bar.CancelIconButton
import com.example.qweasdqwerfd.custom_components.top_bar.ProfileButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController, currentRoute: String) {


    TopAppBar(

        title = {
            Text(
                text = when (currentRoute) {
                    "sign_up" -> "Создать профиль"
                    "all_tasks" -> "Мои доски"
                    "create_task" -> "Создать доску"
                    "profile" -> "Профиль"
                    "forget" -> "Восстановление пароля"
                    else -> ""
                }
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            if (currentRoute == "sign_up") {
                CancelIconButton {
                    navController.navigate("sign_in")
                }
            }
            if (currentRoute == "create_task") {
                CancelIconButton {
                    navController.navigate("all_tasks")
                }
            }
            if (currentRoute == "profile") {
                CancelIconButton {
                    navController.navigate("all_tasks")
                }
            }
            if (currentRoute == "forget") {
                CancelIconButton {
                    navController.navigate("sign_in")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        actions = {
            when (currentRoute) {
                "all_tasks" -> ProfileButton(navController)
            }
        }
    )
}