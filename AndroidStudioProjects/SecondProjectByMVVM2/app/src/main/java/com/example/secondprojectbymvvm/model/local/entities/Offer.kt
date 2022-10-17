package com.example.secondprojectbymvvm.model.local.entities

import androidx.annotation.DrawableRes

data class Offer(
    val name:String,
    val details: String,
    val timeRange:String,
    @DrawableRes val image:Int
)
