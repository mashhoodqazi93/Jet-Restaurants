package com.example.restaurant_impl.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

// The only purpose of this Activity is to be used for UI Testing
// Generally for functionality we are using only one MainActivity
// But for testing we cannot create an instance of MainActivity because
// it will make feature module dependent on app module which is against practices
// All the features should be independent and should not depend on any other feature or app modules

@AndroidEntryPoint
class RestaurantListTestActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantNavigation(navController = rememberNavController())
        }
    }
}