package com.example.qweasdqwerfd.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.create_task.CreateTaskButton
import com.example.qweasdqwerfd.custom_components.create_task.DescriptionField
import com.example.qweasdqwerfd.custom_components.create_task.NameField
import com.example.qweasdqwerfd.custom_components.create_task.PrivateBoardCheckbox

@Composable
fun CreateTaskScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NameField {}
        DescriptionField {}

        Spacer(Modifier.height(10.dp))

        PrivateBoardCheckbox()
        CreateTaskButton(navHostController)
    }
}