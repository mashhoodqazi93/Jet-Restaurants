package com.jet.database.model

import com.google.gson.annotations.SerializedName
import com.jet.database.entities.RestaurantEntity

data class RestaurantsResponse(
    @SerializedName("restaurants")
    val restaurants: List<RestaurantEntity> = emptyList()
)
