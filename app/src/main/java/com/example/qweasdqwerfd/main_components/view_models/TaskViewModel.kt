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

// TaskViewModel.kt

class TaskViewModel : ViewModel() {
    private val taskApi = RetrofitClient.taskApiService

    private val _tasks = mutableStateOf<List<TaskDtoResponse>>(emptyList())
    val tasks: State<List<TaskDtoResponse>> = _tasks

    private val _createState = mutableStateOf<TaskDtoResponse?>(null)
    val createState: State<TaskDtoResponse?> = _createState

    fun fetch(columnId: Long) {
        viewModelScope.launch {
            try {
                val result = taskApi.getTasks(columnId)
                _tasks.value = result
                Log.d("TaskViewModel", "Задачи обновлены: ${result.size}")
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Ошибка при получении задач", e)
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
                val request = taskApi.createTask(
                    CreateTaskDtoRequest(
                        title,
                        description,
                        assigneeId,
                        createdBy,
                        boardId,
                        columnId
                    )
                )
                Log.d("TaskViewModel", "Задача успешно создана: $request")
                fetch(columnId)
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Ошибка при создании задачи: ${e.message}")
            }
        }
    }

    fun deleteTasks(ids: List<Long>, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                ids.forEach { id ->
                    taskApi.deleteTask(id)
                }
                onSuccess()
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Ошибка при удалении задач: ${e.message}")
            }
        }
    }
}


