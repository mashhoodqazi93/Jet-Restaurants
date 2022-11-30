package com.example.restaurant_impl.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.restaurant_impl.ComposeUiEvent
import com.example.restaurant_impl.domain.GetRestaurantsList
import com.example.restaurant_impl.ui.RestaurantNavigation
import com.example.restaurant_impl.ui.viewstates.RestaurantListViewState
import com.jet.database.model.enums.SortOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val getRestaurantsList: GetRestaurantsList
) : JetViewModel() {

    val state = MutableStateFlow(RestaurantListViewState())
    private val savedStateHandle: SavedStateHandle

    init {
        this.savedStateHandle = savedStateHandle
        state.value = state.value.copy(
            currentSortingValue = SortOption.valueOf(
                this.savedStateHandle[SORT_OPTION] ?: SortOption.NONE.name
            )
        )
        getRestaurants()
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
            getRestaurants()
        }
    }

    private fun onSortOptionClicked() {
        navigateTo { RestaurantNavigation.SortOptionDialog }
    }

    private fun onSortOptionSelected(sortOption: SortOption) {
        state.value = state.value.copy(currentSortingValue = sortOption)
        savedStateHandle[SORT_OPTION] = sortOption.name
        getRestaurants()
    }

    sealed class RestaurantsEvent : ComposeUiEvent() {
        data class SearchQueryChanged(val query: String) : RestaurantsEvent()
        object ClearSearchQuery : RestaurantsEvent()
        object SortOptionClicked : RestaurantsEvent()
        data class SortOptionSelected(val selectedSortOption: SortOption) : RestaurantsEvent()
    }

    companion object {
        const val SORT_OPTION = "sort_option"
    }
}