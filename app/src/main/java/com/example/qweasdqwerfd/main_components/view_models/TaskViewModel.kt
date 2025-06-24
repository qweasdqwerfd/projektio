package com.example.qweasdqwerfd.main_components.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.qweasdqwerfd.api.models.tasks.TaskDtoResponse
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.models.tasks.CreateTaskDtoRequest
import kotlinx.coroutines.launch

class   TaskViewModel : ViewModel() {

    private val taskApi = RetrofitClient.taskApiService

    private val _createState = mutableStateOf<TaskDtoResponse?>(null)
    val createState: State<TaskDtoResponse?> = _createState

    private val _tasks = mutableStateOf<List<TaskDtoResponse>>(emptyList())
    val tasks: State<List<TaskDtoResponse>> = _tasks

    fun fetch(boardId: Long) {
        viewModelScope.launch {
            try {
                val response = taskApi.getTasks(boardId)
                _tasks.value = response
                Log.d("TaskViewModel", "Задачи обновлены: ${response.size}")
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Ошибка при получении задач: $e")
            }
        }
    }

    fun create(
        title: String,
        description: String,
        assigneeId: Long,
        createdBy: Long,
        boardId: Long,
        columnId: Long
    ) {
        viewModelScope.launch {
            try {
                Log.d("TaskViewModel", "Создание задачи запущено")
                Log.d("TaskViewModel", "Данные: title=$title, desc=$description, assigneeId=$assigneeId, createdBy=$createdBy, boardId=$boardId, columnId=$columnId")

                val response = taskApi.createTask(
                    CreateTaskDtoRequest(
                        title, description, assigneeId, createdBy, boardId, columnId
                    )
                )

                Log.d("TaskViewModel", "Задача успешно создана: $response")

                // после создания — сразу загружаем заново
                fetch(boardId)

            } catch (e: Exception) {
                Log.e("TaskViewModel", "Ошибка при создании задачи: $e")
            }
        }
    }





}