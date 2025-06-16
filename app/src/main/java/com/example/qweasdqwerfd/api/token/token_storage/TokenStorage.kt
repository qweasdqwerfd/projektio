package com.example.qweasdqwerfd.api.token.token_storage

interface TokenStorage {
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
}
