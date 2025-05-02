package com.example.booksport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.booksport.ui.MainScreen
import com.example.booksport.ui.theme.BookSportTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSportTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("BookSport") }
                        )
                    }
                ) { paddingValues ->
                    MainScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}