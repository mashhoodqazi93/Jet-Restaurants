package com.example.restaurant_impl.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurant_api.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    restaurantRepository: RestaurantRepository
): ViewModel() {
}