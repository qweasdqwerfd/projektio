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

class TaskViewModel : ViewModel() {

    private val taskApi = RetrofitClient.taskApiService

    private val _createState = mutableStateOf<TaskDtoResponse?>(null)
    val createState: State<TaskDtoResponse?> = _createState

    private val _tasks = mutableStateOf<List<TaskDtoResponse>>(emptyList())
    val tasks: State<List<TaskDtoResponse>> = _tasks

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

                _createState.value = request

            } catch (e: Exception) {
                Log.d("task", "create: $e")
            }
        }
    }

    fun getTasks(
        boardId: Long
    ) {
        viewModelScope.launch {
            try {

                val temp = taskApi.getTasks(boardId)

                _tasks.value = temp
            } catch (e: Exception) {
                Log.d("task", "getTasks: $e")
            }
        }
    }
}