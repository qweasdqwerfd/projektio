package com.example.qweasdqwerfd.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.EmailOrNickNameField
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.PasswordSignInField
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.MainDivider
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.CreateProfileButtonMainMenu
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.ForgetPasswordTextButton
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.LogInButton
import com.example.qweasdqwerfd.custom_components.authorization.sign_in.MainIcon

@Composable
fun SignIn(navHostController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


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
                email = it
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

            ForgetPasswordTextButton()
            LogInButton(navHostController)
        }

        MainDivider()

        Spacer(Modifier.height(10.dp))

        CreateProfileButtonMainMenu(navHostController)


    }
}