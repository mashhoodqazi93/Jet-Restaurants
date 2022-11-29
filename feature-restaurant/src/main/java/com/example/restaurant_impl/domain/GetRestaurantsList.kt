package com.example.restaurant_impl.domain

import com.example.restaurant_impl.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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
