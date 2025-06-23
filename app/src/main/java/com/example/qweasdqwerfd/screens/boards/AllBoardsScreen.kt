package com.example.qweasdqwerfd.screens.boards

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.board.Board
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel
import com.example.qweasdqwerfd.main_components.view_models.ColumnViewModel

@Composable
fun AllBoardsScreen(
    boardViewModel: BoardViewModel,
    columnViewModel: ColumnViewModel,
    navController: NavHostController,
    currentColumnTitle: MutableState<String>
) {
    val boards by boardViewModel.boards
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        boardViewModel.fetchAll()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        items(boards) { board ->
            Board(
                board = board,
                onClickBoard = {
                    columnViewModel.clear()
                    currentColumnTitle.value = "Колонка"

                    boardViewModel.selectBoard(board.id)
                    navController.navigate("columns")
                },
                onClickDelete = {
                    boardViewModel.delete(board.id)
                    Toast.makeText(context, "Доска удалена.", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}