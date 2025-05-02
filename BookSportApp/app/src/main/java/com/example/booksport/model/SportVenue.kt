package com.example.booksport.model

data class SportVenue(
    val id: Int,
    val name: String,
    val sportType: String,
    val location: String,
    val pricePerHour: Int // Menambahkan pricePerHour
)