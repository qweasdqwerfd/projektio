package com.example.qweasdqwerfd.api.service

import com.example.qweasdqwerfd.api.models.columns.CreateColumnRequest
import com.example.qweasdqwerfd.api.models.columns.ColumnDataResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ColumnApiService {

    @POST("/api/boards/{boardId}/columns")
    suspend fun createColumn(
        @Path("boardId") boardId: Long,
        @Body request: CreateColumnRequest,
    ): ColumnDataResponse

    @GET("/api/boards/{boardId}/columns")
    suspend fun getColumns(
        @Path("boardId") boardId: Long
    ): List<ColumnDataResponse>

    @DELETE("/api/boards/{boardId}/columns/{columnPosition}")
    suspend fun deleteColumn(
        @Path("boardId") boardId: Long,
        @Path("columnPosition") columnPosition: Int,
    )

}