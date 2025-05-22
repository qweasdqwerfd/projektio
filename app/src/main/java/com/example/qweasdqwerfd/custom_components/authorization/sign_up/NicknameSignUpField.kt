package com.example.qweasdqwerfd.custom_components.authorization.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NicknameSignUpField(
    nickname: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 20
) {
    Column {
        OutlinedTextField(
            value = nickname,
            onValueChange = {
                if (it.length <= maxLength) onValueChange(it)
            },
            label = { Text("Никнейм") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            singleLine = true,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 32.dp, top = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${nickname.length}/$maxLength",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}
