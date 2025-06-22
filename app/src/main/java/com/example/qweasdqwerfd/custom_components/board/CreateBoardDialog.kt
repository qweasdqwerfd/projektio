package com.example.qweasdqwerfd.custom_components.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.dialog.CreateCustomDialog
import com.example.qweasdqwerfd.custom_components.dialog.DescriptionField
import com.example.qweasdqwerfd.custom_components.dialog.NameField
import com.example.qweasdqwerfd.custom_components.dialog.PrivateCheckbox
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel

@Composable
fun CreateBoardDialog(
    navHostController: NavHostController,
    onDismiss: () -> Unit
) {
    val boardViewModel: BoardViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isPrivate by remember { mutableStateOf(false) }

    CreateCustomDialog(
        title = "Создание доски",
        onDismiss = onDismiss,
        nameField = {
            NameField(
                onTitleChanged = { name = it },
                onText = "Название доски"
            )
        },
        privateBoardCheckbox = {
            PrivateCheckbox(
                isChecked = isPrivate,
                onCheckedChange = { isPrivate = it }
            )
        },
        descriptionField = {
            DescriptionField {
                description = it
            }
        },
        onClickCreateButton = {

            boardViewModel.create(
                title = name,
                description = description,
                isPrivate = isPrivate
            )

            onDismiss()
            navHostController.navigate("all_tasks")
        }
    )
}