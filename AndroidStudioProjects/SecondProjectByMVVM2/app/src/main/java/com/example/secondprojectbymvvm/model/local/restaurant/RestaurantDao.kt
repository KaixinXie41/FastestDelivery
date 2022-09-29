package com.example.secondprojectbymvvm.model.local.restaurant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert
    fun insert(restaurant: Restaurant)

    @Delete
    fun delete(restaurant: Restaurant)

    @Query("SELECT * FROM Restaurant")
    fun getAllRestaurant(): LiveData<List<Restaurant>>

    @Query("SELECT * From Restaurant WHERE res_id")
    fun getRestaurantById():LiveData<List<Restaurant>>
}