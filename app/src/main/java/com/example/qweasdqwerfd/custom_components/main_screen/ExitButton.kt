package com.example.qweasdqwerfd.custom_components.main_screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.api.token.token_storage.TokenStorageSingletonImpl
import com.example.qweasdqwerfd.main_components.view_models.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun ExitButton(navHostController: NavHostController, viewModel: AuthViewModel) {

    val context = LocalContext.current
    val tokenStorage = TokenStorageSingletonImpl

    Button(
        onClick = {
            viewModel.viewModelScope.launch {
                val refreshToken = tokenStorage.getRefreshToken()

                if (!refreshToken.isNullOrEmpty()) {
                    viewModel.logout(
                        refreshToken = refreshToken,
                        onSuccess = {

                            viewModel.viewModelScope.launch {
                                tokenStorage.clearTokens()
                            }

                            navHostController.navigate("sign_in") {
                                popUpTo(0) // удаляем ВСЮ историю
                            }
                        },
                        onError = { errorMessage ->
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    )
                } else {
                    Toast.makeText(context, "Токен не найден", Toast.LENGTH_SHORT).show()
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = Color.Red,
        ),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = "Выйти из аккаунта",
        )
    }
}