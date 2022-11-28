package com.example.jetassignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.restaurant_impl.ui.RestaurantActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, RestaurantActivity::class.java))
        finish()
    }
}