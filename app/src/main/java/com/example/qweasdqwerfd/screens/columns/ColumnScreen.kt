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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.tasks_column.ColumnForTasks
import com.example.qweasdqwerfd.custom_components.tasks_column.LocalCurrentColumnTitle
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel

@Composable
fun ColumnScreen(
    columnsViewModel: ColumnViewModel,
    boardViewModel: BoardViewModel,
    currentTitle: MutableState<String>,
    navController: NavHostController,
    showDialog: MutableState<Boolean>
) {

    val columns by columnsViewModel.columns

    val selectedBoardId = boardViewModel.selectBoardId.value
    val currentPos = columnsViewModel.currentPosition

    val context = LocalContext.current


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


        LaunchedEffect(pagerState.currentPage, columns) {
            currentTitle.value = columns[pagerState.currentPage]?.title.toString()
            columnsViewModel.currentPos(pagerState.currentPage)

        }

        CompositionLocalProvider(LocalCurrentColumnTitle provides currentTitle.toString()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                ColumnForTasks(
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
                    }
                )
            }
        }
    }
}