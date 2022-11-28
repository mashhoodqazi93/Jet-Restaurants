package com.jet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurant_impl.database.entities.Restaurant
import com.jet.database.dao.RestaurantDao
import com.jet.database.data.entities.RestaurantStatus

@Database(entities = [Restaurant::class, RestaurantStatus::class], version = 1)
abstract class JetDatabase: RoomDatabase() {
    abstract  fun restaurantDao(): RestaurantDao
}