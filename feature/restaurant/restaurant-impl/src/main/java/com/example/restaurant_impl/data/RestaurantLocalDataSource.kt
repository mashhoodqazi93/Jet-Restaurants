package com.example.restaurant_impl.data

import android.content.Context
import com.jet.database.dao.RestaurantDao
import com.jet.database.model.RestaurantsResponse
import com.example.restaurant_impl.database.entities.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jet.database.model.enums.SortValue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class RestaurantLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context, private val restaurantDao: RestaurantDao
) {

    suspend fun getAllRestaurants(query: String, sortValue: String): List<Restaurant> {
        return restaurantDao.getRestaurantList().filter { it.name.lowercase().contains(query.lowercase()) }.sortedBy {
            it.getSortingValue(SortValue.values().first { sort -> sort.toString() == sortValue })
        }
    }

    fun getAllRestaurantsFlow(): Flow<List<Restaurant>> {
        return restaurantDao.getRestaurantListFlow().distinctUntilChanged()
    }
}