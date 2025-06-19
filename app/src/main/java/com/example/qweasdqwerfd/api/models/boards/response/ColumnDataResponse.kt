package com.example.qweasdqwerfd.api.models.boards.response

import java.time.LocalDateTime

data class ColumnDataResponse(
    val id: Long,
    val title: String,
    val boardId: Long,
    val columnPosition: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)