package com.example.secondprojectbymvvm.model.local.user

data class User(
    var userID: String?,
    var fullName: String?,
    var mobileNo: String?,
    val email: String,
    val password: String
)
