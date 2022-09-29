package com.example.secondprojectbymvvm.model.local.address

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddressDao {

    @Insert
    fun insert(address:Address)

    @Delete
    fun delete(address:Address)

    @Query("SELECT * FROM Address")
    fun getAllAddress():LiveData<List<Address>>

    @Query("SELECT * FROM Address WHERE address_id")
    fun getAddressByAddressId():LiveData<List<Address>>

}