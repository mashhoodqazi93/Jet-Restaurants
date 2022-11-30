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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.restaurant_impl.ComposeUiEvent
import com.example.restaurant_impl.R
import com.example.restaurant_impl.ui.viewmodels.RestaurantListViewModel
import com.jet.database.model.enums.SortOption
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SortingBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    currentSortValue: SortOption,
    handleEvent: (event: ComposeUiEvent) -> Unit

) {
    val coroutineScope = rememberCoroutineScope()
    val hideBottomSheet = {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
        Unit
    }

    Column {
        HeaderWithCross(hideBottomSheet = hideBottomSheet, modifier = Modifier.padding(24.dp))
        Divider(thickness = 1.dp)
        SortingOptions(currentSortValue, handleEvent, hideBottomSheet)
    }
}

@Composable
fun HeaderWithCross(hideBottomSheet: () -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(), Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.sorting_dialog_title), style = MaterialTheme.typography.h6)
        Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            tint = Color.Gray,
            contentDescription = null,
            modifier = Modifier
                .testTag("cross_icon")
                .clickable {
                    hideBottomSheet()
                })
    }
}

@Composable
fun SortingOptions(currentSortOption: SortOption, handleEvent: (ComposeUiEvent) -> Unit, hideBottomSheet:() -> Unit) {
    Column {
        for (sortOption in SortOption.values()) {
            SortOptionItem(
                sortOption = sortOption,
                isSelected = sortOption == currentSortOption,
                handleEvent = handleEvent,
                hideBottomSheet = hideBottomSheet
            )
            Divider(thickness = 1.dp)
        }
    }
}

@Composable
fun SortOptionItem(
    sortOption: SortOption,
    isSelected: Boolean = false,
    handleEvent: (ComposeUiEvent) -> Unit,
    hideBottomSheet: () -> Unit
) {
    val title = stringArrayResource(id = R.array.sorting_options_label)[sortOption.ordinal]

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .clickable {
            handleEvent(
                RestaurantListViewModel.RestaurantsEvent.SortOptionSelected(
                    sortOption
                )
            )
            hideBottomSheet()
        }
        .padding(vertical = 16.dp, horizontal = 24.dp)) {
        RadioButton(selected = isSelected, modifier = Modifier.padding(end = 16.dp), onClick = null)
        Text(text = title)
    }
}