package com.example.restaurant_impl.ui.viewstates

import com.example.restaurant_api.model.Restaurant
import com.example.restaurant_impl.ui.enums.SortValue

data class RestaurantListViewState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val restaurantsList: List<Restaurant> = emptyList(),
    val filteredRestaurantList: List<Restaurant> = emptyList(),
    val currentSortingValue: SortValue = SortValue.STATUS,
    val switchEnabled: Boolean = false
)