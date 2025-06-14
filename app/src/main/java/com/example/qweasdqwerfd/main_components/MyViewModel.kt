package com.example.qweasdqwerfd.main_components

import androidx.annotation.OptIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.models.auth.AuthResponse
import com.example.qweasdqwerfd.api.models.auth.LoginRequest
import com.example.qweasdqwerfd.api.models.auth.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

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
                val response = RetrofitClient.instance.register(
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
                val response = RetrofitClient.instance.login(
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
}
