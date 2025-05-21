package com.example.qweasdqwerfd.main_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.qweasdqwerfd.custom_components.CancelIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {

    var currentRoute by remember { mutableStateOf("") }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute = backStackEntry.destination.route ?: "Ресурсы"
        }
    }

    TopAppBar(

        title = {
            Text(
                text = when (currentRoute) {
                    "sign_up" -> "Создать профиль"
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
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
    )
}