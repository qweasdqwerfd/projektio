package com.example.qweasdqwerfd.custom_components.authorization.sign_in

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ForgetPasswordTextButton() {
    TextButton(
        onClick = {},
        modifier = Modifier.offset(x=-7.dp)
    ) {
        Text(
            text = "Забыли пароль?",
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}