package com.example.qweasdqwerfd

import android.app.Application
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.service.AuthApiService
import com.example.qweasdqwerfd.api.token.AuthRepositoryImpl
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorageSingletonImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        TokenStorageSingletonImpl.init(this)
        RetrofitClient.tokenStorage = TokenStorageSingletonImpl

        // Создаём Retrofit без authInterceptor (чтобы не было цикла)
        val retrofitWithoutAuth = Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authApiService = retrofitWithoutAuth.create(AuthApiService::class.java)
        val authRepository = AuthRepositoryImpl(authApiService)
        RetrofitClient.authRepository = authRepository

    }
}