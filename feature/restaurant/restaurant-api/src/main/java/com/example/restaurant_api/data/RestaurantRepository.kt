package com.example.restaurant_api.data

import com.example.restaurant_api.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurantsList(): List<Restaurant>
}