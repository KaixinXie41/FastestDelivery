package com.example.secondprojectbymvvm.model.local.address

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AddressDao {

    @Insert
    fun insert(address:Address)

    @Delete
    fun delete(address:Address)

    @Update
    fun update(address: Address)

    @Query("SELECT * FROM Address")
    fun getAllAddress():LiveData<List<Address>>

    @Query("SELECT * FROM Address WHERE addressId=:addressId")
    fun getAddressByAddressId(addressId:Int):LiveData<List<Address>>

}