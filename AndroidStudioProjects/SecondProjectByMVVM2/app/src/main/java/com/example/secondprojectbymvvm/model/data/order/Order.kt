package com.example.secondprojectbymvvm.model.data.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true) val order_id: Int = 0,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "title") val address_title: String,
    @ColumnInfo(name = "bill_amount") val bill_amount: String,
    @ColumnInfo(name = "order_date") val order_date: String,
    @ColumnInfo(name = "order_status") val order_status: String,
    @ColumnInfo(name = "payment_method") val payment_method: String
    )