package com.example.qweasdqwerfd.api.models.columns

import java.time.LocalDateTime

data class ColumnDataResponse(
    val id: Long,
    val title: String,
    val boardId: Long,
    val columnPosition: Int,
    val createdAt: String,
    val updatedAt: String
)