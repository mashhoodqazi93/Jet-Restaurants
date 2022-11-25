package com.example.restaurant_impl.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.restaurant_impl.ui.theme.JetAssignmentTheme

@Composable
fun RestaurantListScreen(navController: NavController) {
    JetAssignmentTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text("Mashhood's Restaturant...")
        }
    }
}