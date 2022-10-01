package com.example.secondprojectbymvvm.model.local.restaurant

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface RestaurantDao {

    @Insert(onConflict = REPLACE)
    fun insert(restaurant: Restaurant)

    @Delete
    fun delete(restaurant: Restaurant)

    @Update
    fun update(restaurant: Restaurant)

    @Query("SELECT * FROM Restaurant")
    fun getAllRestaurant(): LiveData<List<Restaurant>>

    @Query("SELECT * From Restaurant WHERE resId=:resId")
    fun getRestaurantById(resId:Int):LiveData<List<Restaurant>>
}