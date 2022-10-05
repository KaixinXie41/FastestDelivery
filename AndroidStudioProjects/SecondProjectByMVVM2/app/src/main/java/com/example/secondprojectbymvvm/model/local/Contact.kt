package com.example.secondprojectbymvvm.model.local

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val contactImage:Int,
    val contactTitle:String,
    val contactWay:String
)
