package com.example.qweasdqwerfd.custom_components.main_screen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.R

@Composable
fun FloatActionBar(navHostController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navHostController.navigate("create_task")
        },
        containerColor = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Icon(
            painter = painterResource(R.drawable.add),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(15.dp)
        )
    }
}
