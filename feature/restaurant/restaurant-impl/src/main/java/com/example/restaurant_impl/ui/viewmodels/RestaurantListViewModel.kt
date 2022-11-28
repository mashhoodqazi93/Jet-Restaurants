package com.example.restaurant_impl.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.restaurant_impl.ComposeUiEvent
import com.example.restaurant_impl.database.entities.Restaurant
import com.example.restaurant_impl.domain.GetRestaurantsList
import com.example.restaurant_impl.domain.ObserveRestaurants
import com.example.restaurant_impl.ui.viewstates.RestaurantListViewState
import com.jet.database.JetDatabase
import com.jet.database.dao.RestaurantDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRestaurantsList: GetRestaurantsList,
    private val observeRestaurants: ObserveRestaurants,
    private val restaurantDao: RestaurantDao
) : ViewModel() {

    val state = MutableStateFlow(RestaurantListViewState())

    init {

        viewModelScope.launch {
            restaurantDao.getRestaurantListFlow().distinctUntilChanged().collect{ list ->
                state.value = state.value.copy(restaurantsList = list)
            }
        }
        /*viewModelScope.launch {
            observeRestaurants.observe().collect{
                Log.v("Restaurants Data Size", it.size.toString())

            }
        }*/
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
            getRestaurantsList(
                GetRestaurantsList.Params(
                    query = state.value.searchQuery,
                    sortValue = state.value.currentSortingValue.toString()
                )
            ).collect {
                state.value = state.value.copy(restaurantsList = it)
            }
        }
    }

    private fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            state.value = state.value.copy(searchQuery = query)
            getRestaurants()
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