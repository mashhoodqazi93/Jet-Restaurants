package com.jet.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.restaurant_impl.database.entities.Restaurant
import com.jet.database.data.entities.RestaurantStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Query(
        "SELECT * FROM restaurant_table " +
                "JOIN restaurant_status_table ON restaurant_table.status = restaurant_status_table.status " +
                "WHERE name LIKE '%' || :query || '%' ORDER BY " +
                "CASE WHEN :sortOrder = 'status' THEN restaurant_status_table.value END ASC," +
                "CASE WHEN :sortOrder = 'distance' THEN distance END ASC," +
                "CASE WHEN :sortOrder = 'bestMatch' THEN best_match END DESC," +
                "CASE WHEN :sortOrder = 'newest' THEN newest END DESC," +
                "CASE WHEN :sortOrder = 'ratingAverage' THEN rating_average END DESC," +
                "CASE WHEN :sortOrder = 'popularity' THEN popularity END DESC," +
                "CASE WHEN :sortOrder = 'averageProductPrice' THEN average_product_price END ASC," +
                "CASE WHEN :sortOrder = 'deliveryCosts' THEN average_product_price END ASC," +
                "CASE WHEN :sortOrder = 'minCost' THEN min_cost END ASC"
    )
    suspend fun getRestaurantList(query: String, sortOrder: String): List<Restaurant>

    @Transaction
    @Query("SELECT * FROM restaurant_table")
    fun getRestaurantListFlow(): Flow<List<Restaurant>>

    @Insert
    suspend fun insertRestaurantStatus(status: List<RestaurantStatus>)
}