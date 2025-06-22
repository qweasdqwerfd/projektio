package com.example.qweasdqwerfd.custom_components.tasks_column

import androidx.compose.runtime.compositionLocalOf

val LocalCurrentColumnTitle = compositionLocalOf<String> {
    "Колонка" // <--- безопасное значение по умолчанию
}