package com.example.qweasdqwerfd.screens.boards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.main_screen.ExitButton
import com.example.qweasdqwerfd.main_components.view_models.AuthViewModel

@Composable
fun ProfileScreen(navHostController: NavHostController, viewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
         Card(
             colors = CardDefaults.cardColors(
                 containerColor = MaterialTheme.colorScheme.surfaceVariant,
                 contentColor = MaterialTheme.colorScheme.onSurfaceVariant
             )
         ) {
             Column(
                 modifier = Modifier
                     .padding(10.dp)
                 ,
                 verticalArrangement = Arrangement.spacedBy(5.dp)
             ) {
                 Text(
                     text = "ID пользователя:",
                     style = MaterialTheme.typography.titleLarge
                 )
                 Text(
                     text = "12",
                     color = MaterialTheme.colorScheme.onSurface,
                     style = MaterialTheme.typography.bodyMedium

                 )
//                 Text(
//                     text = "Логин",
//                     style = MaterialTheme.typography.titleLarge
//                 )
//                 Text(
//                     text = login.toString(),
//                     color = MaterialTheme.colorScheme.onSurface,
//                     style = MaterialTheme.typography.bodyMedium
//
//                 )
//                 Text(
//                     text = "Email",
//                     style = MaterialTheme.typography.titleMedium
//                 )
//                 Text(
//                     text = email.toString(),
//                     color = MaterialTheme.colorScheme.onSurface,
//                     style = MaterialTheme.typography.bodyMedium
//
//                 )
                 Text(
                     text = "Дата регистрации",
                     style = MaterialTheme.typography.titleLarge
                 )
                 Text(
                     text = "26.06.2025",
                     color = MaterialTheme.colorScheme.onSurface,
                     style = MaterialTheme.typography.bodyMedium
                 )
                 ExitButton(
                     navHostController,
                     viewModel
                 )
             }
         }
    }
}