package com.jet.database.entities

import com.google.gson.annotations.SerializedName
import com.jet.database.model.enums.SortValue

data class Restaurant(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sortingValues")
    val sortingValues: SortingOptions,
) {
    fun getSortingValue(sortValue: SortValue): String{
        return when(sortValue){
            SortValue.BEST_MATCH -> sortingValues.bestMatch
            SortValue.NEWEST -> sortingValues.newest
            SortValue.RATING_AVERAGE -> sortingValues.ratingAverage
            SortValue.DISTANCE -> sortingValues.distance.toDouble()
            SortValue.POPULARITY -> sortingValues.popularity
            SortValue.PRODUCT_PRICE_AVERAGE -> sortingValues.averageProductPrice.toDouble()
            SortValue.DELIVERY_COST -> sortingValues.deliveryCosts.toDouble()
            SortValue.MIN_COST -> sortingValues.minCost.toDouble()
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
