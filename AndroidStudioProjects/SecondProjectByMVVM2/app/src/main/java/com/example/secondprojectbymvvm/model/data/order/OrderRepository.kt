package com.example.secondprojectbymvvm.model.data.order

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.address.AppDatabase

class OrderRepository(application: Application) {
    private val orderDao: OrderDao?
    val allOrder: LiveData<List<Order>>

    init {
        val database = application.let{
            AppDatabase.getInstance(it)}
        orderDao = database.getOrderDao()
        allOrder = orderDao.getAllOrder()
    }

    fun insert(order: Order){
        orderDao?.insert(order)
    }

    fun delete(order: Order){
        orderDao?.delete(order)
    }

    fun getOrderByOrderId(orderId:Int){
        orderDao?.getOrderByOrderId(orderId)
    }
}