package com.example.restaurant_impl.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.example.restaurant_impl.R
import com.jet.database.entities.Restaurant
import com.jet.database.model.enums.SortOption

@Composable
fun RestaurantListItem(restaurant: Restaurant, selectedSortOption: SortOption) {
    val selectedSortLabel =
        stringArrayResource(id = R.array.sorting_options_label)[selectedSortOption.ordinal]
    Column(modifier = Modifier
        .clickable { }
        .padding(16.dp)
        .animateContentSize()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = restaurant.name, style = MaterialTheme.typography.body2)
            Text(text = restaurant.status.uppercase(), style = MaterialTheme.typography.overline)
        }
        AnimatedVisibility(selectedSortOption != SortOption.NONE) {
            Text(
                text = "$selectedSortLabel : ${restaurant.getSortingValue(selectedSortOption)}",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.caption
            )
        }
    }
    Divider(thickness = 1.dp)
}