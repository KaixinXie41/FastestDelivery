package com.example.secondprojectbymvvm.model.local.cart

data class Cart(
    var cartId:Long?,
    val mealName:String,
    val mealId:String,
    val mealPrice:Double,
    var count:Int,
    val mealImageUrl:String
)
