package com.example.secondprojectbymvvm.model.local.address

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.model.data.order.OrderDao
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.model.local.restaurant.Restaurant
import com.example.secondprojectbymvvm.model.local.restaurant.RestaurantDao

@Database(entities = [Address::class, Restaurant::class, Order::class, Cart::class], version = 5, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getAddressDao(): AddressDao
    abstract fun getRestaurantDao():RestaurantDao
    abstract fun getOrderDao():OrderDao
    abstract fun getCartDao():CartDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "AppDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}