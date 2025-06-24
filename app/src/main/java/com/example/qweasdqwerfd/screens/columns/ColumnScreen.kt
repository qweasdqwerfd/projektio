package com.example.qweasdqwerfd.screens.columns

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.dialog.CreateCustomDialog
import com.example.qweasdqwerfd.custom_components.dialog.NameField
import com.example.qweasdqwerfd.custom_components.tasks_column.ColumnForTasks
import com.example.qweasdqwerfd.custom_components.tasks_column.LocalCurrentColumnTitle
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel
import com.example.qweasdqwerfd.main_components.view_models.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun ColumnScreen(
    columnsViewModel: ColumnViewModel,
    boardViewModel: BoardViewModel,
    taskViewModel: TaskViewModel,
    currentTitle: MutableState<String>,
    navController: NavHostController,
    showDialog: MutableState<Boolean>,
) {

    val columns by columnsViewModel.columns
    val selectedBoardId = boardViewModel.selectBoardId.value
    val currentPos = columnsViewModel.currentPosition
    val tasks by taskViewModel.tasks

    val coroutineScope = rememberCoroutineScope()
    val selectedPage by columnsViewModel.currentPosition
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val showCreateTaskDialog = remember { mutableStateOf(false) }




    LaunchedEffect(selectedBoardId) {
        if (selectedBoardId != null) {
            Log.d("ColumnScreen", "selectedBoardId = $selectedBoardId")
            columnsViewModel.fetch(selectedBoardId)
        }
    }

    if (columns.isEmpty()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "У вас нет колонок",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Создайте!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

    } else {
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { columns.size }
        )

        LaunchedEffect(selectedPage) {
            selectedPage?.let {
                coroutineScope.launch {
                    pagerState.scrollToPage(it)
                }
                columnsViewModel.currentPos(null) // сброс
            }
        }


        LaunchedEffect(pagerState.currentPage, columns) {
            currentTitle.value = columns[pagerState.currentPage]?.title.toString()
            columnsViewModel.currentPos(pagerState.currentPage)

            if (selectedBoardId != null) {
                taskViewModel.fetch(selectedBoardId)
            }


        }

        CompositionLocalProvider(LocalCurrentColumnTitle provides currentTitle.toString()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val currentColumnId = columns.getOrNull(page)?.id
                val filteredTasks = tasks.filter { it.columnId == currentColumnId }

                ColumnForTasks(
                    tasks = filteredTasks,
                    onClickDelete = {
                        if (selectedBoardId != null) {
                            currentPos.value?.let { pos ->
                                columnsViewModel.delete(
                                    boardId = selectedBoardId,
                                    columnPosition = pos
                                )
                            }
                        }
                        navController.navigate("columns")
                        showDialog.value = false
                        Toast.makeText(
                            context,
                            "Колонка ${currentTitle.value} удалена.",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onClickAdd = {
                        showCreateTaskDialog.value = true
                    }
                )
            }
        }

        if (showCreateTaskDialog.value) {
            CreateCustomDialog(
                title = "Создание задачи",
                onDismiss = { showCreateTaskDialog.value = false },
                nameField = {
                    NameField(
                        onTitleChanged = { name = it },
                        onText = name
                    )
                },
                privateBoardCheckbox = null,
                descriptionField = {
                    NameField(
                        onTitleChanged = { description = it },
                        onText = description
                    )
                },
                onClickCreateButton = {
                    val columnId = columns.getOrNull(pagerState.currentPage)?.id
                    if (selectedBoardId != null && columnId != null) {
                        taskViewModel.create(
                            title = name,
                            description = description,
                            assigneeId = 1L,
                            createdBy = 1L,
                            boardId = selectedBoardId,
                            columnId = columnId
                        )
                        showCreateTaskDialog.value = false
                        name = ""
                        description = ""
                    }
                }


            )
        }

    }
}