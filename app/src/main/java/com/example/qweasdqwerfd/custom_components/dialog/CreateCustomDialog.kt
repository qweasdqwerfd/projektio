package com.example.qweasdqwerfd.custom_components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CreateCustomDialog(
    title: String,
    onDismiss: () -> Unit,
    nameField: @Composable (() -> Unit)? = null,
    privateBoardCheckbox: @Composable (() -> Unit)? = null,
    descriptionField: @Composable (() -> Unit)? = null,
    onClickCreateButton: () -> Unit

) {

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier
                .padding(30.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(12.dp))

                if (nameField != null) {
                    nameField()
                }

                if (descriptionField != null) {
                    descriptionField()
                }

                if (privateBoardCheckbox != null) {
                    privateBoardCheckbox()
                }

                CreateButtonField(
                    onClick = { onClickCreateButton() }
                )
            }


        }
    }
}