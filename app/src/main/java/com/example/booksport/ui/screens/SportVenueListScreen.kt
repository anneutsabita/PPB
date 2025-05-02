package com.example.booksport.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksport.data.VenueRepository
import com.example.booksport.model.SportVenue

@Composable
fun SportVenueListScreen(sport: String, onVenueSelected: (SportVenue) -> Unit) {
    val filteredVenues = VenueRepository.venues.filter { it.sportType == sport }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Available $sport Venues", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredVenues.isEmpty()) {
            Text("No venues available for $sport", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(filteredVenues) { venue ->
                    VenueItem(venue = venue, onClick = { onVenueSelected(venue) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun VenueItem(venue: SportVenue, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = venue.name, style = MaterialTheme.typography.titleMedium)
            Text(text = venue.location, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price per hour: Rp${venue.pricePerHour}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
