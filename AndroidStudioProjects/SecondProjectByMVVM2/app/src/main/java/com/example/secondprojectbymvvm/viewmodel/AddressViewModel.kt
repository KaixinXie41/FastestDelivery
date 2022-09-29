package com.example.secondprojectbymvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.model.local.address.AddressRepository

class AddressViewModel(application: Application): AndroidViewModel(application) {

    private val repository:AddressRepository = AddressRepository(application)

    val allAddress: LiveData<List<Address>> = repository.allAddress
    val getAddress: LiveData<List<Address>> = repository.getAddress

    fun add(address: Address){
        repository.insert(address)
    }

    fun remove(address: Address){
        repository.delete(address)
    }
}