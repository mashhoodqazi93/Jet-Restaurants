package com.jet.database.model

import com.example.restaurant_impl.database.entities.Restaurant
import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant> = emptyList()
)
