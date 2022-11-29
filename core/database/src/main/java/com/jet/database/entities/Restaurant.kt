package com.example.restaurant_impl.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jet.database.model.enums.SortValue

@Entity(tableName = "restaurant_table")
class Restaurant(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0L,

    @SerializedName("name")
    @ColumnInfo(name = "name") val name: String = "",

    @SerializedName("status")
    @ColumnInfo(name = "status") val status: String = "",

    @SerializedName("sortingValues")
    @Embedded
    val sortingValues: SortingValues,
) {

    fun getSortingValue(sortValue: SortValue): Double{
        return when(sortValue){
            SortValue.BEST_MATCH -> sortingValues.bestMatch
            SortValue.NEWEST -> sortingValues.newest
            SortValue.RATING_AVERAGE -> sortingValues.ratingAverage
            SortValue.DISTANCE -> sortingValues.distance.toDouble()
            SortValue.POPULARITY -> sortingValues.popularity
            SortValue.PRODUCT_PRICE_AVERAGE -> sortingValues.averageProductPrice.toDouble()
            SortValue.DELIVERY_COST -> sortingValues.deliveryCosts.toDouble()
            SortValue.MIN_COST -> sortingValues.minCost.toDouble()
        }
    }
}

data class SortingValues(
    @ColumnInfo(name = "best_match")
    @SerializedName("bestMatch") val bestMatch: Double,

    @ColumnInfo(name = "newest")
    @SerializedName("newest") val newest: Double,

    @ColumnInfo(name = "rating_average")
    @SerializedName("ratingAverage") val ratingAverage: Double,

    @ColumnInfo(name = "distance")
    @SerializedName("distance") val distance: Int,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity") val popularity: Double,

    @ColumnInfo(name = "average_product_price")
    @SerializedName("averageProductPrice") val averageProductPrice: Int,

    @ColumnInfo(name = "delivery_costs")
    @SerializedName("deliveryCosts") val deliveryCosts: Int,

    @ColumnInfo(name = "min_cost")
    @SerializedName("minCost") val minCost: Int
)