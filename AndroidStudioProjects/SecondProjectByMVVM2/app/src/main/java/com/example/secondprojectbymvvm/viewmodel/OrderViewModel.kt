package com.example.secondprojectbymvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.model.data.order.OrderRepository

class OrderViewModel(application: Application): AndroidViewModel(application) {
    private val repository: OrderRepository = OrderRepository(application)

    val allOrder: LiveData<List<Order>> = repository.allOrder
    val getOrder: LiveData<List<Order>> = repository.getOrder

    fun add(order:Order){
        repository.insert(order)
    }

    fun remove(order:Order){
        repository.delete(order)
    }
}