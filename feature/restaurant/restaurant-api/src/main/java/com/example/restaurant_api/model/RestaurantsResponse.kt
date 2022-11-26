package com.example.restaurant_api.model

import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant> = emptyList()
)
