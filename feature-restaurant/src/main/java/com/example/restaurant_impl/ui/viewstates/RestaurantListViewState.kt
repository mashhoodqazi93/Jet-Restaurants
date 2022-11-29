package com.example.restaurant_impl.ui.viewstates

import com.jet.database.entities.Restaurant
import com.jet.database.model.enums.SortValue

data class RestaurantListViewState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val restaurantsList: List<Restaurant> = emptyList(),
    val filteredRestaurantList: List<Restaurant> = emptyList(),
    val currentSortingValue: SortValue = SortValue.POPULARITY,
    val switchEnabled: Boolean = false
)