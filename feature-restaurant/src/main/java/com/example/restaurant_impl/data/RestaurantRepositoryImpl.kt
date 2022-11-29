package com.example.restaurant_impl.data

import com.example.restaurant_impl.database.entities.Restaurant
import com.example.restaurant_impl.domain.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val localDataSource: RestaurantLocalDataSource
) : RestaurantRepository {
    override suspend fun getRestaurantsList(query: String, sortValue: String): Flow<List<Restaurant>> {
        return localDataSource.getAllRestaurants(query, sortValue)
    }
}