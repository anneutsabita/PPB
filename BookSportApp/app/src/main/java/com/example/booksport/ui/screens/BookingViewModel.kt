package com.example.booksport.ui.screens

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class BookingViewModel : ViewModel() {
    var selectedDate: Long? by mutableStateOf(null)
    var selectedTime: String? by mutableStateOf(null)

    fun setDate(dateInMillis: Long) {
        selectedDate = dateInMillis
    }

    fun setTime(time: String) {
        selectedTime = time
    }
}
