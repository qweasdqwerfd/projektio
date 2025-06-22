package com.example.qweasdqwerfd.main_components.top_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.example.qweasdqwerfd.custom_components.top_bar.CancelIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    currentRoute: String,
    columnTitle: String?
) {

    val items = listOf("Карточка 1", "Карточка 2", "Карточка 3" /* ... */)


    TopAppBar(
        title = { TopBarTitle(currentRoute = currentRoute, columnTitle = columnTitle) },
        navigationIcon = { TopBarNavigationIcon(currentRoute, navController) },
        actions = { TopBarActions(currentRoute, navController, items) },
        modifier = Modifier.fillMaxWidth(),

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
    )
}