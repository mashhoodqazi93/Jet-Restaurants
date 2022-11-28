package com.example.restaurant_impl.domain

import com.example.restaurant_api.data.RestaurantRepository
import com.example.restaurant_impl.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRestaurantsList @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ResultInteractor<GetRestaurantsList.Params, List<Restaurant>>() {
    override suspend fun doWork(params: Params): List<Restaurant> {
       // restaurantRepository.populateRestaurants()
        return restaurantRepository.getRestaurantsList(params.query, params.sortValue)
    }

    data class Params(
        val query: String,
        val sortValue: String
    )
}

abstract class ResultInteractor<in P, T> {
    operator fun invoke(params: P): Flow<T> {
        return flow<T> {
            emit(doWork(params))
        }.catch { it.printStackTrace() }
    }

    protected abstract suspend fun doWork(params: P): T
}
