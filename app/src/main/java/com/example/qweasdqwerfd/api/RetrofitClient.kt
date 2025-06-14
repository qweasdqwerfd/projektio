package com.example.qweasdqwerfd.api

import com.example.qweasdqwerfd.api.service.AuthApiService
import com.example.qweasdqwerfd.api.token.AuthInterceptor
import com.example.qweasdqwerfd.api.token.AuthRepositoryImpl
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_URL = "http://10.0.2.2:8080/api/"

    lateinit var tokenStorage: TokenStorage
    lateinit var authRepository: AuthRepositoryImpl

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor: Interceptor
        get() = AuthInterceptor(tokenStorage, authRepository)

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()

    val instance: AuthApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }
}