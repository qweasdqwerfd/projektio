package com.example.qweasdqwerfd.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qweasdqwerfd.R
import com.example.qweasdqwerfd.custom_components.EmailOrNickNameField
import com.example.qweasdqwerfd.custom_components.PasswordSignInField
import com.example.qweasdqwerfd.custom_components.MainDivider

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
        Icon(
            painter = painterResource(R.drawable.app),
            contentDescription = "main_icon",
            modifier = Modifier
                .padding(bottom = 20.dp)
                .size(80.dp)
        )

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
            TextButton(
                onClick = {},
                modifier = Modifier.offset(x=-7.dp)
            ) {
                Text(
                    text = "Забыли пароль?",
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Button(
                onClick = {
                    Log.d("skibidi", password)
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.size(width = 160.dp, height = 50.dp)
            ) {
                Text(
                    text = "Войти",
                )
            }
        }

        MainDivider()

        Spacer(Modifier.height(10.dp))

        OutlinedButton(
            onClick = { navHostController.navigate("sign_up") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.Gray), // цвет и толщина обводки
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Text(text = "Создать профиль")
        }


    }
}