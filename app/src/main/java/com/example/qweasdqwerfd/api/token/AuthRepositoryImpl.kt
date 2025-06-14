package com.example.qweasdqwerfd.api.token

import com.example.qweasdqwerfd.api.models.auth.AuthResponse
import com.example.qweasdqwerfd.api.models.auth.RefreshRequest
import com.example.qweasdqwerfd.api.service.AuthApiService

class AuthRepositoryImpl(
    private val api: AuthApiService
) {
    suspend fun refreshToken(refreshToken: String): AuthResponse? {
        return try {
            val response = api.refresh(RefreshRequest(refreshToken))
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}

