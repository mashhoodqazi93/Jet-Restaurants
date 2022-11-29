package com.example.restaurant_impl.domain

import com.jet.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    suspend fun getRestaurantsList(query: String, sortValue: String): Flow<List<Restaurant>>
}