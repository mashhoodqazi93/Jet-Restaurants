package com.example.restaurant_impl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurant_impl.NavigationCommand
import com.example.restaurant_impl.ui.RestaurantNavigation.RestaurantList
import com.example.restaurant_impl.ui.screens.RestaurantListScreen

@Composable
fun RestaurantNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RestaurantList.route) {
        addRestaurantListScreen()
    }
}

private fun NavGraphBuilder.addRestaurantListScreen(){
    composable(RestaurantList.route) {
        RestaurantListScreen()
    }
}

sealed class RestaurantNavigation(val route: String): NavigationCommand() {
    // Screens
    object RestaurantList: RestaurantNavigation("restaurant_list")
    //Dialogs
    object SortOptionDialog: RestaurantNavigation("sort_option")
}