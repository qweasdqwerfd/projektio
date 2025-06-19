package com.example.qweasdqwerfd.api.models.boards.requests

data class CreateBoardRequest(
    val title: String,
    val description: String?,
    val isPrivate: Boolean?
)