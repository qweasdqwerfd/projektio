package com.example.qweasdqwerfd.custom_components.authorization.sign_in

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LogInButton(navHostController: NavHostController) {
    Button(
        onClick = {
            navHostController.navigate("all_tasks")
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier.size(width = 160.dp, height = 50.dp)
    ) {
        Text(
            text = "Войти",
        )
    }
}