package com.example.qweasdqwerfd.main_components.view_models

import android.app.Application
import androidx.annotation.OptIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.models.auth.AuthResponse
import com.example.qweasdqwerfd.api.models.auth.LoginRequest
import com.example.qweasdqwerfd.api.models.auth.RefreshRequest
import com.example.qweasdqwerfd.api.models.auth.RegisterRequest
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorageSingletonImpl
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val authApi = RetrofitClient.authApiService

    private val tokenStorage = TokenStorageSingletonImpl

    private val _accessToken = mutableStateOf<String?>(null)
    val accessToken: State<String?> = _accessToken

    init {
        viewModelScope.launch {
            _accessToken.value = tokenStorage.getAccessToken()
        }
    }

    private val _registrationState = mutableStateOf<Boolean?>(null)
    val registrationState: State<Boolean?> = _registrationState

    private val _loginState = mutableStateOf<Boolean?>(null)
    val loginState: State<Boolean?> = _loginState

    private val _authResponse = mutableStateOf<AuthResponse?>(null)
    val authResponse: State<AuthResponse?> = _authResponse

    @OptIn(UnstableApi::class)
    fun registerUser(login: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authApi.register(
                    RegisterRequest(login, email, password)
                )
                _registrationState.value = response.isSuccessful
                Log.d("authh", "$response")
            } catch (e: Exception) {
                Log.e("authh", "Сетевая ошибка: ${e.message}")
                _registrationState.value = false
            }
        }
    }

    fun resetRegistrationState() {
        _registrationState.value = null
    }

    @OptIn(UnstableApi::class)
    fun loginUser(login: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authApi.login(
                    LoginRequest(login = login, email = email, password = password)
                )
                if (response.isSuccessful) {
                    _authResponse.value = response.body()
                    _loginState.value = true
                } else {
                    _loginState.value = false
                }
                Log.d("authh", "$response")
            } catch (e: Exception) {
                Log.e("authh", "Ошибка логина: ${e.message}")
                _loginState.value = false
            }
        }
    }

    @OptIn(UnstableApi::class)
    fun logout(refreshToken: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authApi.logout(RefreshRequest(refreshToken))

                Log.d("LOGOUT", "Токен перед отправкой logout: $refreshToken")
                Log.d("LOGOUT", "Код ответа: ${response.code()}, тело: ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Ошибка при выходе")
                }
            } catch (e: Exception) {
                onError("Сетевая ошибка: ${e.message}")
            }
        }
    }
}
