package com.example.qweasdqwerfd.screens.boards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.board.Board
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel

@Composable
fun AllBoardsScreen(boardViewModel: BoardViewModel, navController: NavHostController) {
    val boards by boardViewModel.boards

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
                    boardViewModel.selectBoard(board.id)
                    navController.navigate("columns")
                },
                onClickDelete = {
                    boardViewModel.delete(board.id)
                }
            )
        }
    }
}