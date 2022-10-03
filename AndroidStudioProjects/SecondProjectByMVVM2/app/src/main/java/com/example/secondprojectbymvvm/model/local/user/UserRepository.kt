package com.example.secondprojectbymvvm.model.local.user

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.model.local.cart.Cart

class UserRepository(application: Application) {

    private val userDao:UserDao?
    val allUser: LiveData<List<User>>

    init {
        val database = application.let {
            AppDatabase.getInstance(it)
        }
        userDao = database.getUserDao()
        allUser = userDao.getAllUser()
    }

    fun insert(user: User) {
        userDao?.addUser(user)
    }

    fun delete(user: User) {
        userDao?.deleteUser(user)
    }

    fun update(user: User){
        userDao?.updateUser(user)
    }

    fun getUserByUserId(userId:Int){
        userDao?.getUserByUserId(userId)
    }
}