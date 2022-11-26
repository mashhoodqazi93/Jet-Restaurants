package com.example.restaurant_api.data

import com.example.restaurant_api.model.RestaurantsResponse

interface RestaurantRepository {
    suspend fun getRestaurantsList(): RestaurantsResponse
}