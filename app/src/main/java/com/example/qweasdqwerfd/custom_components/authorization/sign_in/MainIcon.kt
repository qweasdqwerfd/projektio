package com.example.qweasdqwerfd.custom_components.authorization.sign_in

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.qweasdqwerfd.R

@Composable
fun MainIcon() {
    Icon(
        painter = painterResource(R.drawable.app),
        contentDescription = "main_icon",
        modifier = Modifier
            .padding(bottom = 20.dp)
            .size(80.dp)
    )
}