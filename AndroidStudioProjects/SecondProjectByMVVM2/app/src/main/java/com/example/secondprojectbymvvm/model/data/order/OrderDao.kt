package com.example.secondprojectbymvvm.model.data.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {

    @Insert
    fun insert(order: Order)

    @Delete
    fun delete(order: Order)

    @Query("SELECT * FROM `Order`")
    fun getAllOrder(): LiveData<List<Order>>

    @Query("SELECT * From `Order` WHERE orderId=:orderId")
    fun getOrderByOrderId(orderId:Int): LiveData<List<Order>>
}