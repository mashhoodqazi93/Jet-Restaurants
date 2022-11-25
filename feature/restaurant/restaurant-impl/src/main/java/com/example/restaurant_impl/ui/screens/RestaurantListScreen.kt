package com.example.restaurant_impl.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.restaurant_impl.R
import com.example.restaurant_impl.ui.theme.JetAssignmentTheme

@Composable
fun RestaurantListScreen(navController: NavController) {
    JetAssignmentTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text("Mashhood's Restaturant...")
        }
    }
}

@Composable
private fun RestaurantListUi() {
    Scaffold(
        topBar = { TopBar(title = stringResource(id = R.string.top_bar_title)) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState())) {

        }
        HeaderSearchAndSort()
    }
}

@Composable
private fun TopBar(title: String) {

}

@Composable
private fun HeaderSearchAndSort() {

}

@Composable
private fun RestaurantListItem(name: String, state: String, sortOption: String) {

}
