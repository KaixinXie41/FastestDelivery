package com.example.secondprojectbymvvm.model.local.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var userId: Int = 0,
    @ColumnInfo(name = "name") var fullName: String?,
    @ColumnInfo(name = "mobile") var mobileNo: String?,
    @ColumnInfo(name = "email")val email: String,
    @ColumnInfo(name = "password")val password: String
)
