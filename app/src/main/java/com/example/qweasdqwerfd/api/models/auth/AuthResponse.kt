package com.example.qweasdqwerfd.api.models.auth

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val userId: Long,
    val email: String
)