package com.example.qweasdqwerfd.custom_components.create_task

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionField(
    onTitleChanged: (String) -> Unit,
) {
    var focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = title,
        onValueChange = {
            title = it
            onTitleChanged(it)
            isError = it.isBlank()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        maxLines = 5,  // Максимум видимых строк
        label = {
            Text(
                text = "Описание",
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        placeholder = { Text("Описание") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        singleLine = false,
        isError = isError,
        supportingText = {
            if (isError) {
                Text("Title cannot be empty", color = MaterialTheme.colorScheme.error)
            }
        }
    )
}