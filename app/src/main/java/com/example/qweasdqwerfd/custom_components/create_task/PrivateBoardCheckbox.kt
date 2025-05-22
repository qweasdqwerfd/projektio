package com.example.qweasdqwerfd.custom_components.create_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrivateBoardCheckbox() {
    var isPrivate by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(-(6).dp),
        modifier = Modifier.offset(y = -5.dp, x = -112.dp)
    ) {
        Checkbox(
            checked = isPrivate,
            onCheckedChange = { isPrivate = it }
        )
        Text(
            text = "Приватная доска",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    // Можно использовать isPrivate для логики
}
