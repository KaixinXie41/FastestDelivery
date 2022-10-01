package com.example.secondprojectbymvvm.model.local.cart

import androidx.room.*

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = true) var cartId:Int?,
    @ColumnInfo(name = "mealName") val mealName:String,
    @ColumnInfo(name = "mealId") val mealId:String,
    @ColumnInfo(name = "mealPrice") val mealPrice:Double,
    @ColumnInfo(name = "count") var count:Int,
    @ColumnInfo(name = "totalPrice") var totalPrice:Double,
    @ColumnInfo(name = "mealImageUrl") val mealImageUrl:String
)
