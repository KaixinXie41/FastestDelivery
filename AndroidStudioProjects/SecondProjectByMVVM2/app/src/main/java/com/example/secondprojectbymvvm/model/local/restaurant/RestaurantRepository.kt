package com.example.secondprojectbymvvm.model.local.restaurant

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.address.AppDatabase

class RestaurantRepository(application: Application) {
    private val restaurantDao: RestaurantDao?
    val allRestaurant: LiveData<List<Restaurant>>

    init {
        val database = application.let {
            AppDatabase.getInstance(it)
        }
        restaurantDao = database.getRestaurantDao()
        allRestaurant = restaurantDao.getAllRestaurant()
    }

    fun insert(restaurant: Restaurant) {
        restaurantDao?.insert(restaurant)
    }

    fun delete(restaurant: Restaurant) {
        restaurantDao?.delete(restaurant)
    }

    fun update(restaurant: Restaurant){
        restaurantDao?.update(restaurant)
    }

    fun getRestaurantById(resId:Int){
        restaurantDao?.getRestaurantById(resId)
    }
}