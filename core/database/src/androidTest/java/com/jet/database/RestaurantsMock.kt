package com.jet.database

import com.jet.database.entities.RestaurantEntity
import com.jet.database.entities.SortingOptionsEntity
import com.jet.database.entities.RestaurantStatus
import com.jet.database.model.enums.SortValue

object RestaurantsMock {

    private val restaurantA = RestaurantEntity(
        id = 1, name = "Tanoshii Sushi", status = "open", sortingValues = SortingOptionsEntity(
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190,
            popularity = 17.0,
            averageProductPrice = 1536,
            deliveryCosts = 200,
            minCost = 1000
        )
    )

    private val restaurantB = RestaurantEntity(
        id = 2, name = "Tandoori Express", status = "closed", sortingValues = SortingOptionsEntity(
            bestMatch = 1.0,
            newest = 266.0,
            ratingAverage = 4.5,
            distance = 2308,
            popularity = 123.0,
            averageProductPrice = 1146,
            deliveryCosts = 150,
            minCost = 1300
        )
    )

    private val restaurantC = RestaurantEntity(
        id = 3, name = "Royal Thai", status = "order ahead", sortingValues = SortingOptionsEntity(
            bestMatch = 2.0,
            newest = 133.0,
            ratingAverage = 4.5,
            distance = 2639,
            popularity = 44.0,
            averageProductPrice = 1492,
            deliveryCosts = 150,
            minCost = 2500
        )
    )

    private val restaurantD = RestaurantEntity(
        id = 4, name = "Sushi One", status = "open", sortingValues = SortingOptionsEntity(
            bestMatch = 3.0,
            newest = 238.0,
            ratingAverage = 4.0,
            distance = 1618,
            popularity = 23.0,
            averageProductPrice = 1285,
            deliveryCosts = 0,
            minCost = 1200
        )
    )

    private val restaurantE = RestaurantEntity(
        id = 5, name = "Roti Shop", status = "open", sortingValues = SortingOptionsEntity(
            bestMatch = 4.0,
            newest = 247.0,
            ratingAverage = 4.5,
            distance = 1605,
            popularity = 81.0,
            averageProductPrice = 915,
            deliveryCosts = 0,
            minCost = 2000
        )
    )

    fun getMockRestaurants(): List<RestaurantEntity> {
        return listOf(restaurantA, restaurantB, restaurantC, restaurantD, restaurantE)
    }

    fun getSortedMockRestaurants(sortValue: SortValue): List<RestaurantEntity> {
        return when (sortValue) {
            SortValue.BEST_MATCH -> {
                listOf(restaurantE, restaurantD, restaurantA, restaurantC, restaurantB)
            }
            SortValue.NEWEST -> {
                listOf(restaurantE, restaurantD, restaurantA, restaurantC, restaurantB)
            }
            SortValue.RATING_AVERAGE -> {
                listOf(restaurantA, restaurantE, restaurantD, restaurantC, restaurantB)
            }
            SortValue.DISTANCE -> {
                listOf(restaurantA, restaurantE, restaurantD, restaurantC, restaurantB)
            }
            SortValue.POPULARITY -> {
                listOf(restaurantE, restaurantD, restaurantA, restaurantC, restaurantB)
            }
            SortValue.PRODUCT_PRICE_AVERAGE -> {
                listOf(restaurantE, restaurantD, restaurantA, restaurantC, restaurantB)
            }
            SortValue.DELIVERY_COST -> {
                listOf(restaurantD, restaurantE, restaurantA, restaurantC, restaurantB)
            }
            SortValue.MIN_COST -> {
                listOf(restaurantA, restaurantD, restaurantE, restaurantC, restaurantB)
            }
            else -> {
                listOf(restaurantA, restaurantD, restaurantE, restaurantC, restaurantB)
            }
        }
    }

    fun getSearchedMockRestaurants(query: String): List<RestaurantEntity> {
        return when (query) {
            "sus" -> listOf(restaurantA, restaurantD)
            "tan" -> listOf(restaurantA, restaurantB)
            else -> emptyList()
        }
    }

    fun restaurantStatusList() = listOf(
        RestaurantStatus("open", 1),
        RestaurantStatus("order ahead", 2),
        RestaurantStatus("closed", 3)
    )
}