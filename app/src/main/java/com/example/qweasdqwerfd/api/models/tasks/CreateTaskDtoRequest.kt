package com.example.qweasdqwerfd.api.models.tasks

data class CreateTaskDtoRequest(
    val title: String,
    val description: String,
    val assigneeId: Long,
    val createdBy: Long,
    val boardId: Long,
    val columnId: Long
)