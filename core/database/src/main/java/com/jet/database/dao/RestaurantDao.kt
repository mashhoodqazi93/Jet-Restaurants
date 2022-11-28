package com.jet.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.restaurant_impl.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Query("SELECT * FROM restaurant_table")
    suspend fun getRestaurantList(): List<Restaurant>

    @Transaction
    @Query("SELECT * FROM restaurant_table")
    fun getRestaurantListFlow(): Flow<List<Restaurant>>
}