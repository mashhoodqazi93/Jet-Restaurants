package com.example.jetassignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetassignment.ui.theme.JetAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint
// Parent Activity and the only one in whole app
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetAssignmentTheme {
                MainNavigation()
            }
        }
    }
}