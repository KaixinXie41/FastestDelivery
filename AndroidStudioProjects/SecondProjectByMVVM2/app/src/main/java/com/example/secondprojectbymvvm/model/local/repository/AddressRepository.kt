package com.example.secondprojectbymvvm.model.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.entities.Address
import com.example.secondprojectbymvvm.model.local.dao.AddressDao

class AddressRepository(application: Application) {
    private val addressDao: AddressDao?
    val allAddress: LiveData<List<Address>>

    init {
        val database = application.let{
            AppDatabase.getInstance(it)}
        addressDao = database.getAddressDao()
        allAddress = addressDao.getAllAddress()
    }

    fun insert(address: Address){
        addressDao?.insert(address)
    }

    fun delete(address: Address){
        addressDao?.delete(address)
    }

    fun update(address: Address){
        addressDao?.update(address)
    }

    fun getAddressByAddressId(addressId:Long){
        addressDao?.getAddressByAddressId(addressId)
    }

}