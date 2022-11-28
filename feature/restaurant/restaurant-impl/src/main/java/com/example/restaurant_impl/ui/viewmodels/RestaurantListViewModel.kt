package com.example.restaurant_impl.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.restaurant_impl.ComposeUiEvent
import com.example.restaurant_impl.domain.GetRestaurantsList
import com.example.restaurant_impl.domain.ObserveRestaurants
import com.example.restaurant_impl.ui.RestaurantNavigation
import com.example.restaurant_impl.ui.viewstates.RestaurantListViewState
import com.jet.database.dao.RestaurantDao
import com.jet.database.model.enums.SortValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRestaurantsList: GetRestaurantsList,
    private val observeRestaurants: ObserveRestaurants,
    private val restaurantDao: RestaurantDao
) : JetViewModel() {

    val state = MutableStateFlow(RestaurantListViewState())

    init {

        viewModelScope.launch {
            restaurantDao.getRestaurantListFlow().distinctUntilChanged().collect{ list ->
                getRestaurants()
            //state.value = state.value.copy(restaurantsList = list)
            }
        }
        viewModelScope.launch {
            restaurantDao.getRestaurantListFlow().distinctUntilChanged().collect{ list ->
                getRestaurants()
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

    fun handleEvents(event: ComposeUiEvent) {
        when (event) {
            is RestaurantsEvent.SearchQueryChanged -> onSearchQueryChanged(event.query)
            is RestaurantsEvent.ClearSearchQuery -> onClearSearchQuery()
            is RestaurantsEvent.SortOptionClicked -> onSortOptionClicked()
            is RestaurantsEvent.SortOptionSelected -> onSortOptionSelected(event.selectedSortOption)
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

    private fun onSortOptionClicked() {
        navigateTo { RestaurantNavigation.SortOptionDialog }
    }

    private fun onSortOptionSelected(sortOption: SortValue) {
        state.value = state.value.copy(currentSortingValue = sortOption)
        getRestaurants()
    }

    sealed class RestaurantsEvent : ComposeUiEvent() {
        data class SearchQueryChanged(val query: String) : RestaurantsEvent()
        object ClearSearchQuery : RestaurantsEvent()
        object SortOptionClicked : RestaurantsEvent()
        data class SortOptionSelected(val selectedSortOption: SortValue): RestaurantsEvent()
    }
}