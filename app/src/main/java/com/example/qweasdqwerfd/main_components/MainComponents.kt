package com.example.qweasdqwerfd.main_components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorageImpl
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.help_const.currentRoute

@Composable
fun MainComponents(viewModel: MyViewModel, context: MainActivity) {
    val navController = rememberNavController()
    val routeState = currentRoute(navController)
    val route = routeState.value

    val tokenStorage = TokenStorageImpl(context)
    val accessToken = produceState<String?>(null) {
        value = tokenStorage.getAccessToken()
    }

    LaunchedEffect(route) {
        Log.d("DEBUG", "route = $route")
    }

    Scaffold(
        topBar = {
            if (route != "sign_in" && route.isNotEmpty()) {
                TopBar(navController, route)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (route == "all_tasks") {
                FloatActionBar(navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (accessToken.value != null) {
                NavGraph(viewModel, navController, accessToken.value)
            }
        }
    }
}
