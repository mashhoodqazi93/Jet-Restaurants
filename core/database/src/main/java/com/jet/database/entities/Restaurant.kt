package com.jet.database.entities

import com.google.gson.annotations.SerializedName
import com.jet.database.model.enums.SortOption

data class Restaurant(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sortingValues")
    val sortingValues: SortingOptions,
) {
    fun getSortingValue(sortValue: SortOption): String{
        return when(sortValue){
            SortOption.BEST_MATCH -> sortingValues.bestMatch
            SortOption.NEWEST -> sortingValues.newest
            SortOption.RATING_AVERAGE -> sortingValues.ratingAverage
            SortOption.DISTANCE -> sortingValues.distance
            SortOption.POPULARITY -> sortingValues.popularity
            SortOption.PRODUCT_PRICE_AVERAGE -> sortingValues.averageProductPrice
            SortOption.DELIVERY_COST -> sortingValues.deliveryCosts
            SortOption.MIN_COST -> sortingValues.minCost
        }.toString()
    }
}

data class SortingOptions(
    @SerializedName("bestMatch")
    val bestMatch: Double,
    @SerializedName("newest")
    val newest: Double,
    @SerializedName("ratingAverage")
    val ratingAverage: Double,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("averageProductPrice")
    val averageProductPrice: Int,
    @SerializedName("deliveryCosts")
    val deliveryCosts: Int,
    @SerializedName("minCost")
    val minCost: Int
)
