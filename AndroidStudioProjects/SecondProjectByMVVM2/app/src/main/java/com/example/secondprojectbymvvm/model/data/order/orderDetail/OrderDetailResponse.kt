package com.example.shoppingappproject.model.remote.data.order.orderDetail

import com.example.secondprojectbymvvm.model.data.order.orderDetail.Order


data class OrderDetailResponse(
    val message: String,
    val order: Order,
    val status: Int
)