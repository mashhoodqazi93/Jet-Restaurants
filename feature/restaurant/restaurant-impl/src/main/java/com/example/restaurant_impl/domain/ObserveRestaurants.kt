package com.example.restaurant_impl.domain

import com.example.restaurant_api.data.RestaurantRepository
import com.example.restaurant_impl.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class ObserveRestaurants @Inject constructor(
    private val restaurantRepository: RestaurantRepository
): SubjectInteractor<Unit, List<Restaurant>>() {
    override fun createObservable(params: Unit): Flow<List<Restaurant>> {
        return restaurantRepository.getRestaurantsListFlow()
    }
}

/**
 * A Flow Interactor that returns a Live data instance, useful for observing database queries
 *
 * DO NOT chain the flow collection with any network calls, as the network call might be triggered
 * twice due to the re-emission by `flatMapLatest()`.
 */
abstract class SubjectInteractor<P : Any, T> {
    private val paramState = MutableStateFlow<P?>(null)

    operator fun invoke(params: P) {
        paramState.value = params
    }

    protected abstract fun createObservable(params: P): Flow<T>

    fun observe(): Flow<T> = paramState.filterNotNull().flatMapLatest { createObservable(it) }

    fun getFlow() = paramState.value?.let { createObservable(it) }
}