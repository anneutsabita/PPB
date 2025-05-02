package com.example.booksport.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.booksport.model.SportVenue
import com.example.booksport.ui.screens.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedSport by remember { mutableStateOf<String?>(null) }
    var selectedVenue by remember { mutableStateOf<SportVenue?>(null) }
    var confirmationMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = selectedSport == null && selectedVenue == null && confirmationMessage == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            // Tampilan untuk memilih cabang olahraga
            SportSelectionScreen(onSportSelected = { sport ->
                selectedSport = sport
                selectedVenue = null // reset venue saat sport diganti
                confirmationMessage = null
            })
        }

        AnimatedVisibility(
            visible = selectedSport != null && selectedVenue == null && confirmationMessage == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            selectedSport?.let { sport ->
                SportVenueListScreen(
                    sport = sport,
                    onVenueSelected = { venue ->
                        selectedVenue = venue
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedVenue != null && confirmationMessage == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            selectedVenue?.let { venue ->
                BookingFormScreen(
                    venue = venue,
                    onBookingConfirmed = { message ->
                        confirmationMessage = message
                        selectedVenue = null
                    },
                    onCancel = {
                        selectedVenue = null
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = confirmationMessage != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ConfirmationScreen(
                confirmationMessage = confirmationMessage!!,
                onBackToHome = {
                    selectedSport = null
                    selectedVenue = null
                    confirmationMessage = null
                }
            )
        }
    }
}