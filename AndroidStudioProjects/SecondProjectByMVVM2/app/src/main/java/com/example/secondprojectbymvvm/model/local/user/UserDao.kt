package com.example.secondprojectbymvvm.model.local.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.secondprojectbymvvm.model.local.cart.Cart

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUser():LiveData<List<User>>

    @Query("SELECT * FROM User WHERE userId=:userId")
    fun getUserByUserId(userId:Int): LiveData<List<User>>

    @Query("DELETE FROM User")
    fun delete()
}