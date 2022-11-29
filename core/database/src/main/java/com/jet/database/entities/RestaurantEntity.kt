package com.jet.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurant_table")
class RestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0L,

    @SerializedName("name")
    @ColumnInfo(name = "name") val name: String = "",

    @SerializedName("status")
    @ColumnInfo(name = "status") val status: String = "",

    @SerializedName("sortingValues")
    @Embedded
    val sortingValues: SortingOptionsEntity,
)

data class SortingOptionsEntity(
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