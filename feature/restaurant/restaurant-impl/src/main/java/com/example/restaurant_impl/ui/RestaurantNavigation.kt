package com.example.restaurant_impl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurant_impl.ui.RestaurantNavigationScreen.RestaurantList
import com.example.restaurant_impl.ui.screens.RestaurantListScreen

@Composable
fun RestaurantNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RestaurantList.route) {
        addRestaurantListScreen(navController)
    }
}

private fun NavGraphBuilder.addRestaurantListScreen(navController: NavController){
    composable(RestaurantList.route) {
        RestaurantListScreen(navController)
    }
}

sealed class RestaurantNavigationScreen(val route: String) {
    object RestaurantList: RestaurantNavigationScreen("restaurant_list")
}