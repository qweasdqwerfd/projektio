package com.example.qweasdqwerfd.main_components.view_models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.models.columns.ColumnDataResponse
import com.example.qweasdqwerfd.api.models.columns.CreateColumnRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ColumnViewModel : ViewModel() {

    private val columnApi = RetrofitClient.columnApiService

    private val _columns = mutableStateOf<List<ColumnDataResponse?>>(emptyList())
    val columns: State<List<ColumnDataResponse?>> = _columns

    private val _createState = mutableStateOf<ColumnDataResponse?>(null)
    val createState: State<ColumnDataResponse?> = _createState

    private val _currentPosition = mutableStateOf<Int?>(null)
    var currentPosition: MutableState<Int?> = _currentPosition

    fun create(boardId: Long, title: String) {
        viewModelScope.launch {
            try {
                Log.d("Column", "Отправка запроса на создание колонки")
                val result = columnApi.createColumn(boardId, CreateColumnRequest(title))
                Log.d("Column", "Колонка создана: $result")
            } catch (e: Exception) {
                Log.e("Column", "Ошибка при создании колонки: ${e.message}")
            }
        }
    }


    fun fetch(boardId: Long) {
        viewModelScope.launch {
            try {
                val request = columnApi.getColumns(boardId)
                _columns.value = request

            } catch (e: HttpException) {
                if (e.code() == 404) {
                    Log.e("columnn", "Нет колонок у доски $boardId (404)")
                    _columns.value = emptyList() // ← корректно обрабатываем
                } else {
                    Log.e("columnn", "HTTP ошибка ${e.code()} ${e.message()}")
                }
            } catch (e: Exception) {
                Log.e("columnn", "Другая ошибка: ${e.message}")
            }
        }
    }


    fun delete(
        boardId: Long,
        columnPosition: Int
    ) {
        viewModelScope.launch {
            try {
                columnApi.deleteColumn(boardId, columnPosition)
                fetch(boardId) // <- обновим список колонок
                Log.d("columnn", "Колонка удалена")
            } catch (e: Exception) {
                Log.e("columnn", "Ошибка при удалении: ${e.message}")
            }
        }
    }


    fun currentPos(curPos: Int) {
        _currentPosition.value = curPos
    }

    fun clear() {
        _columns.value = emptyList()
        _currentPosition.value = 0
    }


}