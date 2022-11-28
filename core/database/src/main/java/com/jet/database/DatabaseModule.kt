package com.jet.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jet.database.dao.RestaurantDao
import com.jet.database.data.entities.RestaurantStatus
import com.jet.database.model.RestaurantsResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @OptIn(DelicateCoroutinesApi::class)
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context, restaurantDaoProvider: Provider<RestaurantDao>
    ): JetDatabase {
        val builder = Room.databaseBuilder(context, JetDatabase::class.java, "jet.db")
        builder.addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //Read Json for extracting restaurants
                val jsonString =
                    context.assets.open("restaurants.json").bufferedReader().use { it.readText() }
                val listType = object : TypeToken<RestaurantsResponse>() {}.type
                val restaurants = Gson().fromJson<RestaurantsResponse>(
                    jsonString, listType
                ).restaurants
                //Pre-populating database
                GlobalScope.launch {
                    restaurantDaoProvider.get().insertRestaurants(restaurants)
                    restaurantDaoProvider.get().insertRestaurantStatus(
                        listOf(
                            RestaurantStatus("open", 1),
                            RestaurantStatus("order ahead", 2),
                            RestaurantStatus("closed", 3)
                        )
                    )
                }
            }

        })
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDao(database: JetDatabase): RestaurantDao = database.restaurantDao()
}
