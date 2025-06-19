package com.example.qweasdqwerfd.api.service

import com.example.qweasdqwerfd.api.models.boards.requests.CreateColumnRequest
import com.example.qweasdqwerfd.api.models.boards.response.ColumnDataResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface ColumnApiService {

    @POST("/boards/{boardId}/columns")
    suspend fun createColumn(
        @Path("boardId") boardId: Long,
        @Body request: CreateColumnRequest,
    ): ColumnDataResponse

    @DELETE("boards/{boardId}/columns/{columnPosition}")
    suspend fun deleteColumn(
        @Path("boardId") boardId: Long,
        @Path("columnPosition") columnPosition: Int,
    )

}