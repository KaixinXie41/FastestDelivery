package com.example.secondprojectbymvvm.model.local.restaurant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey(autoGenerate = true)val resId: Int = 0,
    @ColumnInfo(name = "name") val res_name: String,
    @ColumnInfo(name = "address") val res_address: String,
    @ColumnInfo(name = "rating") val res_rating: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double
)
