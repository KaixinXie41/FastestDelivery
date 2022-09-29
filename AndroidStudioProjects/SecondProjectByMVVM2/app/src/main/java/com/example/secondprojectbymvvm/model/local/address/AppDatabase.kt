package com.example.secondprojectbymvvm.model.local.address

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.model.data.order.OrderDao
import com.example.secondprojectbymvvm.model.local.restaurant.Restaurant
import com.example.secondprojectbymvvm.model.local.restaurant.RestaurantDao

@Database(entities = [Address::class, Restaurant::class, Order::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getAddressDao(): AddressDao
    abstract fun getRestaurantDao():RestaurantDao
    abstract fun getOrderDao():OrderDao

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