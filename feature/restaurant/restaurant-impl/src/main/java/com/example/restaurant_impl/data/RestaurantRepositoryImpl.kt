package com.example.restaurant_impl.data

import android.content.Context
import com.example.restaurant_api.data.RestaurantRepository
import com.example.restaurant_api.model.Restaurant
import com.example.restaurant_api.model.RestaurantsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : RestaurantRepository {
    override suspend fun getRestaurantsList(): RestaurantsResponse {
        val jsonString =
            context.assets.open("restaurants.json").bufferedReader().use { it.readText() }
        val listType = object : TypeToken<RestaurantsResponse>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}