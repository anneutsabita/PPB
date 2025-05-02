package com.example.booksport.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogContent(
    datePickerState: DatePickerState,
    onDismissRequest: () -> Unit,
    onDateSelected: (Long) -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis ?: return@TextButton)
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewDatePickerDialogContent() {
    val datePickerState = rememberDatePickerState()

    PickerDialogContent(
        title = "Pilih Tanggal",
        onDismissRequest = { /* Handle dismiss */ },
        onConfirm = { /* Handle confirm */ },
        content = {
            DatePicker(state = datePickerState)
        }
    )
}
