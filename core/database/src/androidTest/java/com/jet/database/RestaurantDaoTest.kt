package com.jet.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jet.database.dao.RestaurantDao
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
        val result = restaurantDao.getRestaurantList(sortOrder = SortValue.DISTANCE.toString()).first()
        val expectedResult = RestaurantsMock.getSortedMockRestaurants(SortValue.DISTANCE)

        //Assert.assertArrayEquals(expectedResult, result)
        Assert.assertEquals(true, result.equals(expectedResult))
    }

    @After
    fun tearDown() {
        jetDatabase.close()
    }
}