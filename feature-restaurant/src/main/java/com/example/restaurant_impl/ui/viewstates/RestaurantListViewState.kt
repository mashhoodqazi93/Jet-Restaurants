package com.example.restaurant_impl.ui.viewstates

import com.jet.database.entities.Restaurant
import com.jet.database.model.enums.SortOption

data class RestaurantListViewState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val restaurantsList: List<Restaurant> = emptyList(),
    val filteredRestaurantList: List<Restaurant> = emptyList(),
    val currentSortingValue: SortOption = SortOption.NONE,
    val switchEnabled: Boolean = false
)