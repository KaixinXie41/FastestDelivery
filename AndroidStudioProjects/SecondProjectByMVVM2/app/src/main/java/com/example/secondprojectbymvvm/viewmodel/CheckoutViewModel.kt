package com.example.secondprojectbymvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.model.data.order.OrderRepository
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.model.local.address.AddressRepository
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartRepository
import com.example.secondprojectbymvvm.model.local.restaurant.Restaurant
import com.example.secondprojectbymvvm.model.local.restaurant.RestaurantRepository

class CheckoutViewModel(application: Application): AndroidViewModel(application) {
    private val orderRepository: OrderRepository = OrderRepository(application)
    private val cartRepository: CartRepository = CartRepository(application)
    private val addressRepository: AddressRepository = AddressRepository(application)
    private val restaurantRepository: RestaurantRepository = RestaurantRepository(application)


    val allOrder: LiveData<List<Order>> = orderRepository.allOrder
    val allRestaurant: LiveData<List<Restaurant>> = restaurantRepository.allRestaurant
    val allCart: LiveData<List<Cart>> = cartRepository.allCart
    val allAddress: LiveData<List<Address>> = addressRepository.allAddress


    //Order
    fun addOrder(order: Order) {
        orderRepository.insert(order)
    }
    fun removeOrder(order: Order) {
        orderRepository.delete(order)
    }
    fun getOrderByOrderId(orderId: Int) {
        orderRepository.getOrderByOrderId(orderId)
    }

    //Restaurant
    fun addRestaurant(restaurant: Restaurant) {
        restaurantRepository.insert(restaurant)
    }

    fun removeRestaurant(restaurant: Restaurant) {
        restaurantRepository.delete(restaurant)
    }

    fun updateRestaurant(restaurant: Restaurant) {
        restaurantRepository.update(restaurant)
    }

    fun getRestaurantByRestaurantId(resId: Int) {
        restaurantRepository.getRestaurantById(resId)
    }

    //Cart
    val totalAmount = MutableLiveData<Double>()
    var total = 0.0

    fun addCart(cart: Cart) {
        cartRepository.insert(cart)
    }

    fun removeCart(cart: Cart) {
        cartRepository.delete(cart)
    }

    fun updateCart(cart: Cart, isAdded: Boolean) {
        if (isAdded) {
            total += cart.totalPrice
        } else {
            total -= cart.totalPrice
        }
        cartRepository.update(cart)
        totalAmount.postValue(total)
    }

        fun getCartByCartId(cartId: Int) {
            cartRepository.getCartMealByCartId(cartId)
        }

        fun getCartByMealId(mealId: String) {
            cartRepository.getCartMealByMealId(mealId)
        }

        //Address

        fun addAddress(address: Address) {
            addressRepository.insert(address)
        }

        fun removeAddress(address: Address) {
            addressRepository.delete(address)
        }

        fun updateAddress(address: Address) {
            addressRepository.update(address)
        }

        fun getAddressByAddressId(addressId: Int) {
            addressRepository.getAddressByAddressId(addressId)
        }
    }
