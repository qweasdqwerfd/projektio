package com.example.qweasdqwerfd.api

import com.example.qweasdqwerfd.api.service.AuthApiService
import com.example.qweasdqwerfd.api.service.BoardApiService
import com.example.qweasdqwerfd.api.service.ColumnApiService
import com.example.qweasdqwerfd.api.service.TaskApiService
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
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(logging)
            .build()


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Сервисы
    val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val boardApiService: BoardApiService by lazy {
        retrofit.create(BoardApiService::class.java)
    }

    val taskApiService: TaskApiService by lazy {
        retrofit.create(TaskApiService::class.java)
    }

    val columnApiService: ColumnApiService by lazy {
        retrofit.create(ColumnApiService::class.java)
    }
}