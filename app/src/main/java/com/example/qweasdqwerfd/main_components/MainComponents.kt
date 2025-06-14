package com.example.qweasdqwerfd.main_components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorageImpl
import com.example.qweasdqwerfd.custom_components.main_screen.FloatActionBar
import com.example.qweasdqwerfd.help_const.currentRoute

@Composable
fun MainComponents(viewModel: MyViewModel, context: MainActivity) {
    val navController = rememberNavController()
    val tokenStorage = TokenStorageImpl(context)

    var accessToken by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        accessToken = tokenStorage.getAccessToken()
    }

    NavGraph(viewModel, navController, accessToken)
}
