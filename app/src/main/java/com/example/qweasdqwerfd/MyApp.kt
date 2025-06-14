package com.example.qweasdqwerfd

import android.app.Application
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.service.AuthApiService
import com.example.qweasdqwerfd.api.token.AuthRepositoryImpl
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorageImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val tokenStorage = TokenStorageImpl(this)
        RetrofitClient.tokenStorage = tokenStorage

        // Создаём Retrofit без authInterceptor (чтобы не было цикла)
        val retrofitWithoutAuth = Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authApiService = retrofitWithoutAuth.create(AuthApiService::class.java)
        val authRepository = AuthRepositoryImpl(authApiService)
        RetrofitClient.authRepository = authRepository

        // Теперь уже можно безопасно обращаться к RetrofitClient.instance
        val api = RetrofitClient.instance


    }
}
