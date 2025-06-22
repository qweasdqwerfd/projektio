package com.example.qweasdqwerfd.main_components.view_models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.models.boards.requests.CreateBoardRequest
import com.example.qweasdqwerfd.api.models.boards.response.BoardDataResponse
import kotlinx.coroutines.launch

class BoardViewModel : ViewModel() {

    private val boardApi = RetrofitClient.boardApiService

    private val _boards = mutableStateOf<List<BoardDataResponse>>(emptyList())
    val boards: State<List<BoardDataResponse>> = _boards

    private var _createState = mutableStateOf<BoardDataResponse?>(null)
    val createState: State<BoardDataResponse?> = _createState

    private val _selectBoardId = mutableStateOf<Long?>(null)
    var selectBoardId: MutableState<Long?> = _selectBoardId


    fun create(title: String, description: String?, isPrivate: Boolean?) {
        viewModelScope.launch {
            try {

                val request = boardApi.createBoard(
                    CreateBoardRequest(title, description, isPrivate)
                )
                _createState.value = request

            } catch (e: Exception) {
                Log.d("boardd", "createBoard: $e")
            }
        }
    }

    fun fetchAll() {
        viewModelScope.launch {
            try {
                val result = boardApi.getAllBoards()

                _boards.value = result.content

            } catch (e: Exception) {
                Log.d("boardd", "fetchAllBoards: $e")
            }
        }
    }

    fun delete(boardId: Long) {
        viewModelScope.launch {
            try {
                boardApi.deleteBoard(boardId)
                _boards.value = _boards.value.filter { it.id != boardId }

            } catch (e: Exception) {
                Log.d("boardd", "delete: $e")
            }
        }
    }

    fun selectBoard(boardId: Long) {
        _selectBoardId.value = boardId
        Log.d("columnn", "fetching columns for boardId = $boardId")
    }
}