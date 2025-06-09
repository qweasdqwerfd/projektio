package com.example.qweasdqwerfd.api.models.auth

data class RegisterRequest(
    val login: String,
    val email: String,
    val password: String
)