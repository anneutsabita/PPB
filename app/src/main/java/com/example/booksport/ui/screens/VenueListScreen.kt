package com.example.booksport.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksport.model.SportVenue

@Composable
fun VenueListScreen(venues: List<SportVenue>, onVenueSelected: (SportVenue) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(venues) { venue ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onVenueSelected(venue) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = venue.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = venue.sportType, style = MaterialTheme.typography.bodyMedium)
                    Text(text = venue.location, style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "Rp${venue.pricePerHour.toInt()} / jam",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

