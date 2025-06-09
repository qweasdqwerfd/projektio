package com.example.qweasdqwerfd.api.token

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStorage: TokenStorage,
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val accessToken = runBlocking {
            tokenStorage.getAccessToken()
        }

        request = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        var response = chain.proceed(request)

        if (response.code == 401) {
            response.close()

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

