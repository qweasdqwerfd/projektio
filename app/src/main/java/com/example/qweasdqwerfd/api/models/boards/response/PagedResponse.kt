package com.example.qweasdqwerfd.api.models.boards.response

data class PagedResponse<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalPages: Int
)
