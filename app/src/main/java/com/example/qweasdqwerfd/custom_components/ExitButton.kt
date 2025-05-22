package com.example.qweasdqwerfd.custom_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun ExitButton(navHostController: NavHostController) {
    Button(
        onClick = {
            navHostController.navigate("sign_in")
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = Color.Red,
        ),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = "Выйти из аккаунта",
        )
    }
}