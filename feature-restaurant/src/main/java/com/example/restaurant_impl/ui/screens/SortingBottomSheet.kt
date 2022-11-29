package com.example.restaurant_impl.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.restaurant_impl.ComposeUiEvent
import com.example.restaurant_impl.R
import com.example.restaurant_impl.ui.viewmodels.RestaurantListViewModel
import com.jet.database.model.enums.SortValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SortingBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    currentSortValue: SortValue,
    handleEvent: (event: ComposeUiEvent) -> Unit

) {
    Column(modifier = Modifier.padding(24.dp)) {
        HeaderWithCross(bottomSheetState)
        Divider(thickness = 1.dp)
        SortingOptions(currentSortValue,handleEvent)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeaderWithCross(bottomSheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(), Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.sorting_dialog_title))
        Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            tint = Color.Gray,
            contentDescription = null,
            modifier = Modifier.testTag("cross_icon")
                .clickable {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            })
    }
}

@Composable
fun SortingOptions(currentSortValue: SortValue, handleEvent: (ComposeUiEvent) -> Unit) {
    Column {
        for (sortOption in SortValue.values()) {
            SortOptionItem(title = sortOption.name, isSelected = sortOption == currentSortValue, handleEvent = handleEvent)
            Divider(thickness = 1.dp)
        }
    }
}

@Composable
fun SortOptionItem(title: String, isSelected: Boolean = false, handleEvent: (ComposeUiEvent) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = isSelected, onClick = { handleEvent(
            RestaurantListViewModel.RestaurantsEvent.SortOptionSelected(
                SortValue.valueOf(title)
            )
        ) }, modifier = Modifier.padding(end = 16.dp))
        Text(text = title)
    }
}