package com.jet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jet.database.entities.RestaurantEntity
import com.jet.database.dao.RestaurantDao
import com.jet.database.entities.RestaurantStatus

@Database(entities = [RestaurantEntity::class, RestaurantStatus::class], version = 1)
abstract class JetDatabase: RoomDatabase() {
    abstract  fun restaurantDao(): RestaurantDao
}