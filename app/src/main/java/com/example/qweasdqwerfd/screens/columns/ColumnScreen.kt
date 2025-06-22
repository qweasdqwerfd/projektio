package com.example.qweasdqwerfd.screens.columns

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qweasdqwerfd.custom_components.tasks_column.ColumnForTasks
import com.example.qweasdqwerfd.custom_components.tasks_column.LocalCurrentColumnTitle
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel

@Composable
fun ColumnScreen(
    viewModelColumns: ColumnViewModel,
    boardViewModel: BoardViewModel
) {

    val columns by viewModelColumns.columns

    val selectedBoardId = boardViewModel.selectBoardId.value

    LaunchedEffect(selectedBoardId) {
        if (selectedBoardId != null) {
            Log.d("ColumnScreen", "selectedBoardId = $selectedBoardId")
            viewModelColumns.fetch(selectedBoardId)
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

        var currentTitle by remember { mutableStateOf(columns.first()?.title) }

        LaunchedEffect(pagerState.currentPage) {
            currentTitle = columns[pagerState.currentPage]?.title
        }

        CompositionLocalProvider(LocalCurrentColumnTitle provides currentTitle.toString()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                ColumnForTasks()
            }
        }
    }
}