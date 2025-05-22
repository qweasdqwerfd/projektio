package com.example.qweasdqwerfd.custom_components.authorization.sign_up

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CreateProfile(password: String, confirmPassword: String, onPasswordMismatch: (String?) -> Unit) {
    OutlinedButton(
        onClick = {
            if (password != confirmPassword) {
                onPasswordMismatch("Пароли не совпадают")
            } else {
                onPasswordMismatch(null)
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