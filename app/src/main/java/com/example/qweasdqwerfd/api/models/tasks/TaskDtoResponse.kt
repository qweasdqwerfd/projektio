package com.example.qweasdqwerfd.api.models.tasks

data class TaskDtoResponse(
    val id: Long,
    val title: String,
    val columnId: Long,
    val boardId: Long,
    val description: String,
    val assigneeId: Long,
    val createdBy: Long,
    val createdAt: String,
    val lastUpdatedAt: String,
    val dueDate: String,
    val position: Int
)