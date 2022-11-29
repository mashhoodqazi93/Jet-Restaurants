package com.jet.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_status_table")
class RestaurantStatus(
    @PrimaryKey
    @ColumnInfo(name = "status") var status: String,
    @ColumnInfo(name = "value") var value: Int
)