package com.example.restaurant_impl.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_impl.ComposeUiEvent
import com.example.restaurant_impl.domain.GetRestaurantsList
import com.example.restaurant_impl.ui.viewstates.RestaurantListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val getRestaurantsList: GetRestaurantsList
) : ViewModel() {

    val state = MutableStateFlow(RestaurantListViewState())


    init {
        getRestaurants()
    }

    fun getStateFlow(): StateFlow<RestaurantListViewState> {
        return state.asStateFlow()
    }

    fun handleEvents(event: RestaurantsEvent) {
        when (event) {
            is RestaurantsEvent.SearchQueryChanged -> onSearchQueryChanged(event.query)
            RestaurantsEvent.ClearSearchQuery -> onClearSearchQuery()

        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            getRestaurantsList.collect {
                state.value = state.value.copy(restaurantsList = it.restaurants)
            }
        }
    }

    private fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            state.value = state.value.copy(searchQuery = query)
        }
    }

    private fun onClearSearchQuery() {
        viewModelScope.launch {
            state.value = state.value.copy(searchQuery = "")
        }
    }

    sealed class RestaurantsEvent : ComposeUiEvent() {
        data class SearchQueryChanged(val query: String) : RestaurantsEvent()
        object ClearSearchQuery : RestaurantsEvent()
    }
}