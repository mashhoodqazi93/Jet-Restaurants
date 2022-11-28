package com.example.restaurant_impl.data

import com.example.restaurant_api.data.RestaurantRepository
import com.example.restaurant_impl.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val localDataSource: RestaurantLocalDataSource
) : RestaurantRepository {
    override fun getRestaurantsListFlow(): Flow<List<Restaurant>> {
        return localDataSource.getAllRestaurantsFlow()
    }

    override suspend fun getRestaurantsList(query: String, sortValue: String): List<Restaurant> {
        return localDataSource.getAllRestaurants(query, sortValue)
    }
}