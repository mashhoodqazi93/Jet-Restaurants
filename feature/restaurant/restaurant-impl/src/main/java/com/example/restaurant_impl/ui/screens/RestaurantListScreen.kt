package com.example.restaurant_impl.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.restaurant_api.model.Restaurant
import com.example.restaurant_impl.R
import com.example.restaurant_impl.ui.viewmodels.RestaurantListViewModel
import com.example.restaurant_impl.ui.viewmodels.RestaurantListViewModel.RestaurantsEvent

@Composable
fun RestaurantListScreen(navController: NavController) {
    val viewModel: RestaurantListViewModel = hiltViewModel()
    RestaurantListUi(viewModel)
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun RestaurantListUi(viewModel: RestaurantListViewModel) {
    val stateFlow = viewModel.state.collectAsStateWithLifecycle()
    val state = stateFlow.value
    val restaurants = state.restaurantsList
    val searchQuery = state.searchQuery

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
            HeaderSearchAndSort(searchQuery, viewModel::handleEvents)
            RestaurantList(restaurants)
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
            modifier = Modifier.background(color = MaterialTheme.colors.primary)
        ) {
            SimpleTextField(
                value = query,
                onValueChange = { handleEvent(RestaurantsEvent.SearchQueryChanged(it)) },
                textStyle = MaterialTheme.typography.body2,
                placeholderText = "Search for your favourite restaurant",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(40.dp)
                    .background(
                        color = MaterialTheme.colors.background, shape = RoundedCornerShape(20.dp)
                    ),
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = null,
                            modifier = Modifier.clickable { handleEvent(RestaurantsEvent.ClearSearchQuery) })
                    }
                },
                fontSize = MaterialTheme.typography.caption.fontSize,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun RestaurantList(restaurants: List<Restaurant>) {
    restaurants.forEach { restaurant ->
        RestaurantListItem(restaurant.name, restaurant.status, "")
    }
}

@Composable
private fun RestaurantListItem(name: String, status: String, sortOption: String) {
    Column(modifier = Modifier
        .clickable { }
        .padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = name, style = MaterialTheme.typography.body2)
            Text(text = status, style = MaterialTheme.typography.body2)
        }
        Text(
            text = "Distance: 15.0 km",
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
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
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
