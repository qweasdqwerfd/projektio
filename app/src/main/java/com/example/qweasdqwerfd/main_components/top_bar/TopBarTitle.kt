package com.example.qweasdqwerfd.main_components.top_bar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.qweasdqwerfd.custom_components.tasks_column.LocalCurrentColumnTitle

@Composable
fun TopBarTitle(
    currentRoute: String,
    columnTitle: String?,
    selectedCount: Int = 0
) {
    val title = when (currentRoute) {
        "sign_up" -> "Создать профиль"
        "all_tasks" -> "Мои доски"
        "profile" -> "Профиль"
        "forget" -> "Восстановление пароля"
        "columns" -> {
            if (selectedCount > 0) {
                "$selectedCount выбрано"
            } else {
                columnTitle ?: "Колонка"
            }
        }
        else -> ""
    }

    Text(text = title)
}

