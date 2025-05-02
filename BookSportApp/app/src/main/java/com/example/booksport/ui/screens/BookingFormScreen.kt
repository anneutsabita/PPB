package com.example.booksport.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.unit.dp
import android.widget.Toast

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingFormScreen(
    venue: com.example.booksport.model.SportVenue,
    onBookingConfirmed: (String) -> Unit,
    onCancel: () -> Unit,
    bookingViewModel: BookingViewModel = viewModel() // ViewModel injected here
) {
    val context = LocalContext.current
    val selectedDateMillis = bookingViewModel.selectedDate
    val selectedTime = bookingViewModel.selectedTime
    val selectedSport = remember { mutableStateOf<String?>(null) } // kalau ada dropdown olahraga


    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Booking for: ${venue.name}")
        Text(text = "Location: ${venue.location}")
        Text(text = "Price per hour: Rp${venue.pricePerHour}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDatePicker = true }) {
            Text("Pilih Tanggal")
        }

        // ✅ Tampilkan tanggal yang dipilih
        if (selectedDateMillis != null) {
            val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            val formattedDate = sdf.format(Date(selectedDateMillis))
            Text(text = "Booking untuk Tanggal: $formattedDate")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { showTimePicker = true }) {
            Text("Pilih Jam")
        }

        // ✅ Tampilkan waktu yang dipilih
        if (selectedTime != null) {
            Text(text = "Booking untuk Jam: $selectedTime")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                if (selectedDateMillis != null && selectedTime != null) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val bookingDateTime = "${sdf.format(Date(selectedDateMillis))} $selectedTime"
                    onBookingConfirmed("Booking confirmed for ${venue.name} on $bookingDateTime")
                } else {
                    // Displaying a toast if date or time is not selected
                    Toast.makeText(context, "Please select both date and time.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Confirm Booking")
            }

            Button(onClick = onCancel) {
                Text(text = "Cancel")
            }
        }

        // Show Date Picker Dialog
        if (showDatePicker) {
            DatePickerDialogContent(
                datePickerState = datePickerState,
                onDismissRequest = { showDatePicker = false },
                onDateSelected = { dateInMillis ->
                    bookingViewModel.setDate(dateInMillis)
                    showDatePicker = false
                }
            )
        }

        if (showTimePicker) {
            TimePickerDialogContent(
                timePickerState = timePickerState,
                onDismissRequest = { showTimePicker = false },
                onTimeSelected = { hour, minute ->
                    val formattedTime = java.lang.String.format("%02d:%02d", hour, minute)
                    bookingViewModel.setTime(formattedTime)
                    showTimePicker = false
                }
            )
        }
    }
}
