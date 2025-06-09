package com.example.qweasdqwerfd.api.models.auth

data class LoginRequest(
    val login: String? = null,
    val email: String? = null,
    val password: String
)