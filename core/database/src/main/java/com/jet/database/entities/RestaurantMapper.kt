package com.jet.database.entities

object RestaurantMapper {

    fun toRestaurantList(restaurantEntityList: List<RestaurantEntity>): List<Restaurant> {
        val restaurantList = mutableListOf<Restaurant>()
        restaurantEntityList.forEach { restaurantEntity ->
            restaurantList.add(
                Restaurant(
                    name = restaurantEntity.name,
                    status = restaurantEntity.status,
                    sortingValues = SortingOptions(
                        bestMatch = restaurantEntity.sortingValues.bestMatch,
                        newest = restaurantEntity.sortingValues.newest,
                        ratingAverage = restaurantEntity.sortingValues.ratingAverage,
                        distance = restaurantEntity.sortingValues.distance,
                        popularity = restaurantEntity.sortingValues.popularity,
                        averageProductPrice = restaurantEntity.sortingValues.averageProductPrice,
                        deliveryCosts = restaurantEntity.sortingValues.deliveryCosts,
                        minCost = restaurantEntity.sortingValues.minCost,
                    )
                )
            )
        }
        return restaurantList
    }
}