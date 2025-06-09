package com.example.qweasdqwerfd.screens.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.CreateProfile
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.EmailField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.NicknameSignUpField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.PasswordSignUpField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.SecondPassword
import com.example.qweasdqwerfd.main_components.MyViewModel

@Composable
fun SignUpScreen(
    navGraph: NavHostController,
    viewModel: MyViewModel,
) {
    var nickname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val registrationState by viewModel.registrationState



    Column(
        modifier = Modifier
            .fillMaxSize(),

        ) {
        NicknameSignUpField(
            nickname = nickname,
            onValueChange = { nickname = it }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy((-14).dp)
        ) {
            EmailField {
                email = it
            }
            PasswordSignUpField {
                password = it
                confirmPasswordError = null
            }
        }
        SecondPassword(
            onPasswordChanged = {
                confirmPassword = it
                confirmPasswordError = null // сбрасываем при изменении
            },
            externalError = confirmPasswordError
        )

        Spacer(Modifier.height(12.dp))

        CreateProfile(
            onRegisterClicked = {
                if (password == confirmPassword) {
                    viewModel.registerUser(nickname, email, password)
                    Log.d("authh", nickname)
                    Log.d("authh", email)
                    Log.d("authh", password)

                } else {
                    confirmPasswordError = "Пароли не совпадают"
                }
            }
        )

        when (registrationState) {
            null -> { /* ничего */ }

            true -> {
                Text("Регистрация успешна!", color = Color.Green)
                navGraph.navigate("all_tasks")

                // После чего сбрасывать состояние, например:
                LaunchedEffect(Unit) {
                    viewModel.resetRegistrationState()
                }
            }

            false -> {
                Text("Ошибка регистрации", color = Color.Red)
                // Сброс ошибки по желанию или повторная попытка
            }
        }

    }
}

