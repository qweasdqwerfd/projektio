package com.example.qweasdqwerfd.custom_components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CancelIconButton(
    onClick: () -> Unit
) {

    IconButton(
        onClick = onClick
    ) {
        Icon(
            Icons.Default.ArrowBack,
            tint = Color.Black,
            contentDescription = "cancel",
            modifier = Modifier
                .size(24.dp)
                .offset(x = 5.dp),
        )
    }

}