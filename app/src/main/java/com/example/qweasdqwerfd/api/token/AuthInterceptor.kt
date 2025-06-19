package com.example.qweasdqwerfd.api.token

import android.util.Log
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStorage: TokenStorage,
    private val authRepository: AuthRepositoryImpl,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val path = request.url.encodedPath

        // Не добавляем токен, если это login или register
        if (
            !path.contains("/auth/login") &&
            !path.contains("/auth/register") &&
            !path.contains("/auth/logout")
        ) {
            val accessToken = runBlocking {
                tokenStorage.getAccessToken()
            }

            Log.d("AuthInterceptor", "Токен: Bearer $accessToken")

            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        }

        var response = chain.proceed(request)

        // Обработка истекшего токена
        if (response.code == 401 && !path.contains("/auth/login") && !path.contains("/auth/register")) {

            val refreshToken = runBlocking {
                tokenStorage.getRefreshToken()
            }

            val newTokens = runBlocking {
                authRepository.refreshToken(refreshToken ?: "")
            }

            return if (newTokens != null) {
                runBlocking {
                    tokenStorage.saveTokens(newTokens.accessToken, newTokens.refreshToken)
                }

                val newRequest = request.newBuilder()
                    .header("Authorization", "Bearer ${newTokens.accessToken}")
                    .build()

                chain.proceed(newRequest)
            } else {
                response
            }
        }



        return response
    }
}
