package com.example.qweasdqwerfd.main_components

import androidx.annotation.OptIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.qweasdqwerfd.api.RetrofitClient
import com.example.qweasdqwerfd.api.models.auth.LoginRequest
import com.example.qweasdqwerfd.api.models.auth.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
class MyViewModel: ViewModel() {

    // Состояние регистрации: null — ничего, true — успех, false — ошибка
    private val _registrationState = mutableStateOf<Boolean?>(null)
    val registrationState: State<Boolean?> = _registrationState

    private val _loginState = mutableStateOf<Boolean?>(null)
    val loginState: State<Boolean?> = _loginState




    @OptIn(UnstableApi::class)
    fun registerUser(
        login: String,
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            try {


                val response = RetrofitClient.instance.register(
                    RegisterRequest(login, email, password)
                )

                if (response.isSuccessful) {
                    _registrationState.value = true
                } else {
                    // Можно сюда положить логику для разных ошибок,
                    // например response.code() == 409 -> пользователь уже есть
                    _registrationState.value = false
                }

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

    fun loginUser(
        login: String,
        email: String,
        password: String,
    ) {

        try {
            viewModelScope.launch {
                val response = RetrofitClient.instance.login(LoginRequest(
                    login = login,
                    email = email,
                    password = password
                ))

                if (response.isSuccessful) {
                    _loginState.value = true
                } else {
                    _loginState.value = false
                }

                Log.d("authh", "$response")



            }
        } catch (e: Exception) {
            Log.e("authh", e.toString())
        }
    }
}