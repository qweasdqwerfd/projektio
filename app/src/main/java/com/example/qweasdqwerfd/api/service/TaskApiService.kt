package com.example.qweasdqwerfd.api.service

import com.example.qweasdqwerfd.api.models.tasks.CreateTaskDtoRequest
import com.example.qweasdqwerfd.api.models.tasks.TaskDtoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskApiService {

    @POST("/api/tasks")
    suspend fun createTask(
        @Body task: CreateTaskDtoRequest
    ): TaskDtoResponse

    @GET("/api/tasks")
    suspend fun getTasks(
        @Query("boardId") boardId: Long
    ): List<TaskDtoResponse>


    @DELETE("/api/tasks/{taskId}")
    suspend fun deleteTask(
        @Path("taskId") taskId: Long
    ): Response<Unit> // Используем Response<Unit> для получения status code
}