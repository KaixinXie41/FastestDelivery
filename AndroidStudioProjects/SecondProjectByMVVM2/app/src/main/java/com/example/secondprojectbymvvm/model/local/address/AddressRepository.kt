package com.example.secondprojectbymvvm.model.local.address

import android.app.Application
import androidx.lifecycle.LiveData

class AddressRepository(application: Application) {
    private val addressDao:AddressDao?
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

    fun getAddressByAddressId(addressId:Int){
        addressDao?.getAddressByAddressId(addressId)
    }

}