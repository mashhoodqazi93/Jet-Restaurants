package com.example.jetassignment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.restaurant_impl.ui.RestaurantNavigation

@Composable
// This is the Main Navigation.
// All of the feature Navigation Composables will be included here as Nested Navigation
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    RestaurantNavigation(navController = navController)
}