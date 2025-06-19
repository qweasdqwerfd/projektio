package com.example.qweasdqwerfd.screens.boards

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.qweasdqwerfd.custom_components.create_board.Board
import com.example.qweasdqwerfd.main_components.view_models.BoardViewModel

@Composable
fun AllBoardsScreen(boardViewModel: BoardViewModel) {
    val boards by boardViewModel.boards

    LaunchedEffect(Unit) {
        boardViewModel.fetchAll()
    }

    LazyColumn {
        items(boards) { board ->
            Board(
                board = board,
                onClickBoard = {

                },
                onClickDelete = {
                    boardViewModel.delete(board.id)
                }
            )
        }
    }
}