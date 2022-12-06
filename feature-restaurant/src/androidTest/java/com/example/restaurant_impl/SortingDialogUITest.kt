package com.example.restaurant_impl

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SortingDialogUITest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule (order = 1)
    val rule = createAndroidComposeRule<RestaurantListTestActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun sortButtonVisibilityTest() {
        rule.onNodeWithTag("sort_icon").assertIsDisplayed()
    }

    @Test
    fun sortDialogVisibilityTest() {
        rule.onNodeWithTag("sort_icon").performClick()
        rule.onNodeWithText(text = "Select Sorting Option").assertIsDisplayed()
    }

    @Test
    fun sortDialogDismissalTest() {
        rule.onNodeWithTag("sort_icon").performClick()
        rule.onNodeWithTag("cross_icon").performClick()
        rule.onNodeWithText(text = "Select Sorting Option").assertIsNotDisplayed()
    }
}