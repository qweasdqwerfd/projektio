package com.example.qweasdqwerfd.main_components.top_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.top_bar.CancelIconButton

@Composable
fun TopBarNavigationIcon(currentRoute: String, navController: NavHostController) {
    when (currentRoute) {
        "sign_up", "forget" -> CancelIconButton {
            navController.navigate("sign_in")
        }
        "profile", "columns" -> CancelIconButton {
            navController.navigate("all_tasks")
        }
    }
}
