package com.example.restaurant_impl.data

import com.jet.database.dao.RestaurantDao
import com.jet.database.entities.Restaurant
import com.jet.database.entities.RestaurantMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantLocalDataSource @Inject constructor(
    private val restaurantDao: RestaurantDao
) {

    fun getAllRestaurants(query: String, sortValue: String): Flow<List<Restaurant>> {
        return restaurantDao.getRestaurantList(query, sortValue).map { RestaurantMapper.toRestaurantList(it) }
    }
}