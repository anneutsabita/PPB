package com.example.booksport.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksport.data.VenueRepository
import com.example.booksport.ui.screens.*
import androidx.compose.animation.AnimatedVisibility

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportSelectionScreen(onSportSelected: (String) -> Unit) {
    var selectedSport by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val sports = remember {
        VenueRepository.venues.map { it.sportType }.distinct()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Select a Sport",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedSport,
                onValueChange = {}, // Tidak dapat diubah secara manual, hanya bisa memilih
                readOnly = true,
                label = { Text("Select a Sport") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                sports.forEach { sport ->
                    DropdownMenuItem(
                        text = { Text(sport) },
                        onClick = {
                            selectedSport = sport
                            expanded = false
                            onSportSelected(sport) // Panggil callback dengan sport yang dipilih
                        }
                    )
                }
            }
        }

        // Menampilkan VenueListScreen setelah memilih olahraga
        AnimatedVisibility(visible = selectedSport.isNotEmpty()) {
            val filteredVenues = VenueRepository.venues.filter { it.sportType == selectedSport }
            if (filteredVenues.isNotEmpty()) {
                VenueListScreen(venues = filteredVenues) { selectedVenue ->
                    println("Venue Selected: ${selectedVenue.name}")
                }
            } else {
                Text("No venues available for $selectedSport", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}