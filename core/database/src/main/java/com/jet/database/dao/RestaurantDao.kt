package com.jet.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jet.database.entities.RestaurantEntity
import com.jet.database.entities.RestaurantStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert
    suspend fun insertRestaurants(restaurants: List<RestaurantEntity>)

    @Query(
        "SELECT * FROM restaurant_table " +
                "JOIN restaurant_status_table ON restaurant_table.status = restaurant_status_table.status " +
                "WHERE name LIKE '%' || :query || '%' ORDER BY " +
                "restaurant_status_table.value ASC," +
                "CASE WHEN :sortOrder = 'distance' THEN distance END ASC," +
                "CASE WHEN :sortOrder = 'bestMatch' THEN best_match END DESC," +
                "CASE WHEN :sortOrder = 'newest' THEN newest END DESC," +
                "CASE WHEN :sortOrder = 'ratingAverage' THEN rating_average END DESC," +
                "CASE WHEN :sortOrder = 'popularity' THEN popularity END DESC," +
                "CASE WHEN :sortOrder = 'averageProductPrice' THEN average_product_price END ASC," +
                "CASE WHEN :sortOrder = 'deliveryCosts' THEN average_product_price END ASC," +
                "CASE WHEN :sortOrder = 'minCost' THEN min_cost END ASC"

    )
    fun getRestaurantList(query: String = "", sortOrder: String? = null): Flow<List<RestaurantEntity>>

/*    @Query("SELECT * FROM restaurant_table")
    fun getRestaurantListTest(): Flow<List<Restaurant>>*/

    @Insert
    suspend fun insertRestaurantStatus(status: List<RestaurantStatus>)
}