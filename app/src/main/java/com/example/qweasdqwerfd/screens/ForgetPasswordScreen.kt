package com.example.qweasdqwerfd.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.EmailOrNickNameField
import com.example.qweasdqwerfd.custom_components.authorization.sign_up.SecondPassword
import com.example.qweasdqwerfd.custom_components.forget_password.ContinueButton
import com.example.qweasdqwerfd.custom_components.forget_password.NewPassword

@Composable
fun ForgetPasswordScreen(navHostController: NavHostController) {

    var emailOrNickName by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var repeatPasswordError by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailOrNickNameField {
            emailOrNickName = it
        }

        NewPassword {
            newPassword = it
        }

        SecondPassword (
            onPasswordChanged = {
                repeatPassword = it
                repeatPasswordError = null // сбрасываем при изменении
            },
            externalError = repeatPasswordError
        )

        ContinueButton(
            newPassword,
            repeatPassword,
            onPasswordMismatch = { repeatPasswordError = it },
            navHostController
        )
    }

}