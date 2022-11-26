package com.example.restaurant_impl.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.restaurant_impl.ui.theme.JetAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetAssignmentTheme {
                RestaurantNavigation(navController = rememberNavController())
            }
        }
    }
}