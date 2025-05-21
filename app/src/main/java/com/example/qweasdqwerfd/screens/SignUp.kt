package com.example.qweasdqwerfd.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qweasdqwerfd.custom_components.EmailField
import com.example.qweasdqwerfd.custom_components.NicknameSignUpField
import com.example.qweasdqwerfd.custom_components.PasswordSignUpField
import com.example.qweasdqwerfd.custom_components.SecondPassword

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

        OutlinedButton(
            onClick = {
                if (password != confirmPassword) {
                    confirmPasswordError = "Пароли не совпадают"
                } else {
                    // Всё ок — создаём профиль
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 15.dp),
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.Gray), // цвет и толщина обводки
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Создать профиль")
        }


    }


}