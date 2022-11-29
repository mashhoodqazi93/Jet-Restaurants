package com.example.restaurant_impl.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.restaurant_impl.NavigationCommand
import com.jet.database.model.enums.SortValue
import com.example.restaurant_impl.R
import com.example.restaurant_impl.ui.RestaurantNavigation
import com.example.restaurant_impl.ui.viewmodels.RestaurantListViewModel
import com.example.restaurant_impl.ui.viewmodels.RestaurantListViewModel.RestaurantsEvent
import com.jet.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun RestaurantListScreen(viewModel: RestaurantListViewModel = hiltViewModel()) {
    val nav = viewModel.navigation
    RestaurantListUi(viewModel, nav)
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun RestaurantListUi(viewModel: RestaurantListViewModel, navigation: Flow<NavigationCommand>) {
    val stateFlow = viewModel.state.collectAsStateWithLifecycle()
    val state = stateFlow.value
    val restaurants = state.restaurantsList
    val searchQuery = state.searchQuery
    val currentSortValue = state.currentSortingValue
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val handleEvent = viewModel::handleEvents
    val openBottomSheet = {
        coroutineScope.launch {
            bottomSheetState.show()
        }
    }
    coroutineScope.launch {
        navigation.collect{ nav ->
            when (nav) {
                is RestaurantNavigation.SortOptionDialog -> {
                    openBottomSheet()
                }
            }
        }
    }




    ModalBottomSheetLayout(sheetState = bottomSheetState, sheetContent = { SortingBottomSheet(bottomSheetState = bottomSheetState, handleEvent = handleEvent, currentSortValue = currentSortValue)}, sheetShape = RoundedCornerShape(16.dp)) {
        Scaffold(
            topBar = { TopBar(title = stringResource(id = R.string.top_bar_title)) },
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                HeaderSearchAndSort(searchQuery, viewModel::handleEvents)
                RestaurantList(restaurants, currentSortValue)
            }
        }
    }
}

@Composable
private fun TopBar(title: String) {
    TopAppBar {
        Text(text = title, modifier = Modifier.padding(start = 16.dp))
    }
}

@Composable
private fun HeaderSearchAndSort(query: String, handleEvent: (RestaurantsEvent) -> Unit) {
    val focusManager = LocalFocusManager.current

    Column(Modifier.background(MaterialTheme.colors.primary)) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            SimpleTextField(
                value = query,
                onValueChange = { handleEvent(RestaurantsEvent.SearchQueryChanged(it)) },
                textStyle = MaterialTheme.typography.body2,
                placeholderText = stringResource(id = R.string.search_hint),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(40.dp)
                    .weight(0.95f)
                    .background(
                        color = MaterialTheme.colors.background, shape = RoundedCornerShape(20.dp)
                    ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_search),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(18.dp)
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = null,
                            modifier = Modifier.clickable { handleEvent(RestaurantsEvent.ClearSearchQuery) })
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
            )
            Icon(painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .testTag("sort_icon")
                    .weight(0.05f)
                    .size(22.dp)
                    .clickable {
                        handleEvent(RestaurantsEvent.SortOptionClicked)
                    })
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun RestaurantList(restaurants: List<Restaurant>, selectedSortValue: SortValue) {
    restaurants.forEach { restaurant ->
        RestaurantListItem(restaurant, selectedSortValue)
    }
}

@Composable
private fun RestaurantListItem(restaurant: Restaurant, selectedSortValue: SortValue) {
    Column(modifier = Modifier
        .clickable { }
        .padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = restaurant.name, style = MaterialTheme.typography.body2)
            Text(text = restaurant.status, style = MaterialTheme.typography.body2)
        }
        Text(
            text = "$selectedSortValue : ${restaurant.getSortingValue(selectedSortValue)}",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.caption
        )
    }
    Divider(thickness = 1.dp)
}

@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(Color.Black),
) {
    BasicTextField(modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        interactionSource = interactionSource,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onTextLayout = onTextLayout,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->
            Row(
                modifier, verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) Text(
                        placeholderText, style = textStyle
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        })
}
