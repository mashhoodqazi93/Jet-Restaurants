package com.example.restaurant_impl.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurant_impl.R
import com.example.restaurant_impl.ui.theme.JetAssignmentTheme

@Composable
fun RestaurantListScreen(navController: NavController) {
    RestaurantListUi()
}

@Composable
private fun RestaurantListUi() {
    Scaffold(
        topBar = { TopBar(title = stringResource(id = R.string.top_bar_title)) },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

        }
        HeaderSearchAndSort()
    }
}

@Composable
private fun TopBar(title: String) {
    TopAppBar(modifier = Modifier.padding(start = 16.dp)) {
        Text(text = title)
    }
}

@Composable
private fun HeaderSearchAndSort() {

}

@Composable
private fun RestaurantListItem(name: String, state: String, sortOption: String) {

}
