package com.example.restaurant_impl.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
}