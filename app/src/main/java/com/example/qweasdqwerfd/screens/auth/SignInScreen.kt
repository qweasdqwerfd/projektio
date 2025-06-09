package com.example.qweasdqwerfd.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.EmailOrNickNameField
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.PasswordSignInField
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.MainDivider
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.CreateProfileButtonMainMenu
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.ForgetPasswordTextButton
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.LogInButton
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.MainIcon
import com.example.qweasdqwerfd.main_components.MyViewModel

@Composable
fun SignInScreen(
    navHostController: NavHostController,
    viewModel: MyViewModel,
) {

    var emailOrNick by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        MainIcon()

        Column(
            verticalArrangement = Arrangement.spacedBy((-14).dp)
        ) {

            Spacer(Modifier.heightIn(10.dp))

            EmailOrNickNameField {
                emailOrNick = it
            }
            PasswordSignInField {
                password = it
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ForgetPasswordTextButton {
                navHostController.navigate("forget")
            }
            LogInButton {
                viewModel.loginUser(
                    login = emailOrNick,
                    email = emailOrNick,
                    password = password
                )

            }
        }

        MainDivider()

        Spacer(Modifier.height(10.dp))

        CreateProfileButtonMainMenu {
            navHostController.navigate("sign_up")
        }

        when (loginState) {
            true -> navHostController.navigate("all_tasks")
            false -> Text(text = "Неправильный логин или пароль", color = Color.Red)
            else -> Text("")
        }


    }
}