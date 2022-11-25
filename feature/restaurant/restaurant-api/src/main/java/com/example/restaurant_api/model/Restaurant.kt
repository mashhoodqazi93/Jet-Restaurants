package com.example.restaurant_api.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("sortingValues") val sortingValues: SortingValues,
)
