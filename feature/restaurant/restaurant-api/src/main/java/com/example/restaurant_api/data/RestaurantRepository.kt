package com.example.restaurant_api.data

import com.example.restaurant_impl.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getRestaurantsListFlow(): Flow<List<Restaurant>>
    suspend fun getRestaurantsList(query: String, sortValue: String): List<Restaurant>
}