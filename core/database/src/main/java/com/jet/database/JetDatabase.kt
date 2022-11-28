package com.jet.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jet.database.dao.RestaurantDao
import com.example.restaurant_impl.database.entities.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jet.database.model.RestaurantsResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Database(entities = [Restaurant::class], version = 1)
abstract class JetDatabase: RoomDatabase() {
    abstract  fun restaurantDao(): RestaurantDao
}