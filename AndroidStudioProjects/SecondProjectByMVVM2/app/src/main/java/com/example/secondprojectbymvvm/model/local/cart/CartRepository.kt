package com.example.secondprojectbymvvm.model.local.cart

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.address.AppDatabase

class CartRepository (application: Application) {
    private val cartDao: CartDao?
    val allCart: LiveData<List<Cart>>

    init {
        val database = application.let {
            AppDatabase.getInstance(it)
        }
        cartDao = database.getCartDao()
        allCart = cartDao.getAllCart()
    }

    fun insert(cart: Cart) {
        cartDao?.addCart(cart)
    }

    fun delete(cart:Cart) {
        cartDao?.deleteCart(cart)
    }

    fun update(cart:Cart){
        cartDao?.updateCart(cart)
    }

    fun getCartMealByCartId(cartId:Int){
        cartDao?.getCartMealByCartId(cartId)
    }

    fun getCartMealByMealId(mealId:String){
        cartDao?.getCartMealByMealId(mealId)
    }
}