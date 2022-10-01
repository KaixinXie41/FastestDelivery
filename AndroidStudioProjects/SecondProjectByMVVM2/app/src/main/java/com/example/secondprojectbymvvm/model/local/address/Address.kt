package com.example.secondprojectbymvvm.model.local.address

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)val addressId: Int = 0,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "title") val title: String
)
