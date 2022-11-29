package com.example.restaurant_impl.data

import android.content.Context
import com.jet.database.dao.RestaurantDao
import com.example.restaurant_impl.database.entities.Restaurant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class RestaurantLocalDataSource @Inject constructor(
    private val restaurantDao: RestaurantDao
) {

    fun getAllRestaurants(query: String, sortValue: String): Flow<List<Restaurant>> {
        return restaurantDao.getRestaurantList(query, sortValue)
    }
}