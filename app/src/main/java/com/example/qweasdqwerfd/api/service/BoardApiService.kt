package com.example.qweasdqwerfd.api.service

import com.example.qweasdqwerfd.api.models.boards.requests.CreateBoardRequest
import com.example.qweasdqwerfd.api.models.boards.response.BoardDataResponse
import com.example.qweasdqwerfd.api.models.boards.response.PagedResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardApiService {

    @POST("boards")
    suspend fun createBoard(
        @Body board: CreateBoardRequest,
    ): BoardDataResponse

    @GET("boards")
    suspend fun getAllBoards(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): PagedResponse<BoardDataResponse>

    @DELETE("boards/{boardId}")
    suspend fun deleteBoard(
        @Path("boardId") boardId: Long,
    ): retrofit2.Response<Unit>


    @GET("boards/current")
    suspend fun getCurrentBoard(): BoardDataResponse






}