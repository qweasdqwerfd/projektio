package com.example.qweasdqwerfd.main_components.top_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.qweasdqwerfd.custom_components.tasks_column.cards_for_columns.CardsDialog
import com.example.qweasdqwerfd.custom_components.top_bar.CustomActionButton

@Composable
fun TopBarActions(
    currentRoute: String,
    navController: NavController,
    items: List<String>
) {
    val showDialog = remember { mutableStateOf(false) }

    when (currentRoute) {

        "all_tasks" -> CustomActionButton(
            text = "Профиль",
            onClick = { navController.navigate("profile") }
        )

        "columns" -> CustomActionButton(
            text = "Все колонки",
            onClick = { showDialog.value = true }
        )
    }

    if (showDialog.value) {
        CardsDialog(
            cardItems = items,
            onDismiss = { showDialog.value = false },
            onClickCard = {}
        )
    }
}
