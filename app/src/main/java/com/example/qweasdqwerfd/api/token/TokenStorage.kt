package com.example.qweasdqwerfd.api.token

interface TokenStorage {
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
}
