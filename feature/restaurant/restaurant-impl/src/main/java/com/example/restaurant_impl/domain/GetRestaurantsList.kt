package com.example.restaurant_impl.domain

import com.example.restaurant_api.data.RestaurantRepository
import com.example.restaurant_api.model.RestaurantsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetRestaurantsList @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : Flow<RestaurantsResponse> {
    override suspend fun collect(collector: FlowCollector<RestaurantsResponse>) {
        val restaurantResponse = restaurantRepository.getRestaurantsList()
        collector.emit(restaurantResponse)
    }
}