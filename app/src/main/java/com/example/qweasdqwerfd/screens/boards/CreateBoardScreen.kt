package com.example.qweasdqwerfd.screens.boards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.create_board.CreateBoardButton
import com.example.qweasdqwerfd.custom_components.create_board.DescriptionField
import com.example.qweasdqwerfd.custom_components.create_board.NameField
import com.example.qweasdqwerfd.custom_components.create_board.PrivateBoardCheckbox
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel

@Composable
fun CreateBoardScreen(navHostController: NavHostController) {

    val boardViewModel: BoardViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isPrivate by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NameField {
            name = it
        }
        DescriptionField {
            description = it
        }

        Spacer(Modifier.height(10.dp))

        PrivateBoardCheckbox(
            isChecked = isPrivate,
            onCheckedChange = { isPrivate = it }
        )
        CreateBoardButton(
            onClick = {
                boardViewModel.create(
                    title = name,
                    description = description,
                    isPrivate = isPrivate
                )

                navHostController.navigate("all_tasks")
            },
        )
    }
}