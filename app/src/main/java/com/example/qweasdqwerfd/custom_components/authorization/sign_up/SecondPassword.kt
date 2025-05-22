package com.example.qweasdqwerfd.custom_components.authorization.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.qweasdqwerfd.R

@Composable
fun SecondPassword(
    label: String = "Пароль",
    onPasswordChanged: (String) -> Unit,
    externalError: String? = null // <- добавлено
) {
    val focusManager = LocalFocusManager.current
    var password by remember { mutableStateOf("") }
    var isInternalError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val isError = isInternalError || externalError != null

    Column {
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                onPasswordChanged(it)
                isInternalError = it.isBlank()
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1,
            singleLine = true,
            label = { Text(label, color = MaterialTheme.colorScheme.onSurface) },
            placeholder = { Text(label, color = MaterialTheme.colorScheme.onSurface) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(R.drawable.eye_on)
                else
                    painterResource(R.drawable.eye_off)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = image,
                        contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 4.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            isError = isError,
            supportingText = {
                if (isInternalError) {
                    Text("Пароль не может быть пустым", color = MaterialTheme.colorScheme.error)
                } else if (externalError != null) {
                    Text(externalError, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 32.dp)
                .offset(y=-12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${password.length}/${32}",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }

    }

}