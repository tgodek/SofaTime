package com.mangatalabs.core_ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultAlertDialog(
    title: String,
    description: String,
    confirmButtonText: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            Button(onClick = {
                onConfirmButtonClick()
            }) {
                Text(text = confirmButtonText)
            }
        },
    )
}

@Preview
@Composable
fun DefaultAlertDialogPreview() {
    DefaultAlertDialog(
        title = "Some Title",
        description = "Some description",
        confirmButtonText = "OK",
        onDismissRequest = {},
        onConfirmButtonClick = {}
    )
}