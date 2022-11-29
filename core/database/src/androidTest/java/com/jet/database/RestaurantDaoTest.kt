package com.jet.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jet.database.dao.RestaurantDao
import com.jet.database.entities.RestaurantMapper
import com.jet.database.model.enums.SortValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RestaurantDaoTest {

    private lateinit var jetDatabase: JetDatabase
    private lateinit var restaurantDao: RestaurantDao

    @Before
    fun setup() {
        jetDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), JetDatabase::class.java
        ).allowMainThreadQueries().build()
        restaurantDao = jetDatabase.restaurantDao()
        runBlocking {
            restaurantDao.insertRestaurantStatus(RestaurantsMock.restaurantStatusList())
        }
    }

    @Test
    fun insertRestaurantsTest() = runBlocking {
        val restaurantList = RestaurantsMock.getMockRestaurants()
        restaurantDao.insertRestaurants(restaurantList)
        val result = restaurantDao.getRestaurantList().first()
        Assert.assertEquals(restaurantList.size, result.size)
    }

    @Test
    fun sortingTest() = runBlocking {
        val restaurantList = RestaurantsMock.getMockRestaurants()
        restaurantDao.insertRestaurants(restaurantList)

        // Testing for All Sorting Options
        SortValue.values().forEach { sortingOption ->
            val result = RestaurantMapper.toRestaurantList(
                restaurantDao.getRestaurantList(sortOrder = sortingOption.toString()).first()
            )
            val expectedResult = RestaurantMapper.toRestaurantList(
                RestaurantsMock.getSortedMockRestaurants(sortingOption)
            )
            Assert.assertEquals(true, result == expectedResult)
        }
    }

    @Test
    fun searchTest() = runBlocking {
        val searchQueryList = listOf<String>("sus", "tan")
        val restaurantList = RestaurantsMock.getMockRestaurants()
        restaurantDao.insertRestaurants(restaurantList)

        searchQueryList.forEach { query ->
            val result = RestaurantMapper.toRestaurantList(
                restaurantDao.getRestaurantList(query = query).first()
            )
            val expectedResult =
                RestaurantMapper.toRestaurantList(RestaurantsMock.getSearchedMockRestaurants(query))
            Assert.assertEquals(true, result == expectedResult)
        }
    }

    @After
    fun tearDown() {
        jetDatabase.close()
    }
}