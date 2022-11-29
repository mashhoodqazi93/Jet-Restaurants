package com.example.restaurant_impl.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_impl.NavigationCommand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class JetViewModel: ViewModel() {
    private var _navigation: MutableSharedFlow<NavigationCommand> = MutableSharedFlow()
    val navigation: Flow<NavigationCommand> = _navigation

    protected fun navigateTo(navigate: () -> NavigationCommand) {
        viewModelScope.launch {
            _navigation.emit(navigate())
        }
    }
}