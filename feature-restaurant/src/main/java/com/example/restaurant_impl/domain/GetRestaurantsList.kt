package com.example.restaurant_impl.domain

import com.jet.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantsList @Inject constructor(
    private val restaurantRepository: RestaurantRepository
){
    suspend operator fun invoke(params: Params): Flow<List<Restaurant>> {
        return restaurantRepository.getRestaurantsList(params.query, params.sortValue)
    }

    data class Params(
        val query: String,
        val sortValue: String
    )
}
