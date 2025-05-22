package com.example.qweasdqwerfd.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.EmailField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.NicknameSignUpField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.PasswordSignUpField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.SecondPassword
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.CreateProfile

@Preview(showBackground = true)
@Composable
fun SignUp() {
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }


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
            EmailField { }
            PasswordSignUpField {
                password = it
                confirmPasswordError = null
            }
        }
        SecondPassword(
            label = "Повторите пароль",
            onPasswordChanged = {
                confirmPassword = it
                confirmPasswordError = null // сбрасываем при изменении
            },
            externalError = confirmPasswordError
        )

        Spacer(Modifier.height(12.dp))

        CreateProfile(
            password,
            confirmPassword,
            onPasswordMismatch = { confirmPasswordError = it }
        )
    }
}