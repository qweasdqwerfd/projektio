package com.example.qweasdqwerfd.custom_components.tasks_column

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.dialog.CreateCustomDialog
import com.example.qweasdqwerfd.custom_components.dialog.NameField
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel

@Composable
fun CreateColumnDialog(
    navController: NavHostController,
    onDismiss: () -> Unit,
    boardViewModel: BoardViewModel,
    columnViewModel: ColumnViewModel,
) {

    val boardId = boardViewModel.selectBoardId.value


    var name by remember { mutableStateOf("") }

    CreateCustomDialog(
        title = "Создание колонны",
        onDismiss = onDismiss,
        nameField = {
            NameField(
                onTitleChanged = { name = it },
                onText = "Название колонны"
            )
        },
        privateBoardCheckbox = null,
        descriptionField = null,
        onClickCreateButton = {

            if (boardId != null) {
                columnViewModel.create(
                    boardId = boardId,
                    title = name
                )
            }




        }
    )
}