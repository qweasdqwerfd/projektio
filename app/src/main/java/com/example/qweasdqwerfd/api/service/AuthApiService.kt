package com.example.qweasdqwerfd.api.service

import com.example.qweasdqwerfd.api.models.auth.AuthResponse
import com.example.qweasdqwerfd.api.models.auth.LoginRequest
import com.example.qweasdqwerfd.api.models.auth.RefreshRequest
import com.example.qweasdqwerfd.api.models.auth.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("auth/refresh")
    suspend fun refresh(@Body request: RefreshRequest): Response<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(@Body request: RefreshRequest): Response<Map<String, String>>
}
