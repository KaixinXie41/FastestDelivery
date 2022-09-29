package com.example.secondprojectbymvvm.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCartBinding
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.DeliveryFragment
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.DineInFragment
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.PickupFragment
import com.example.secondprojectbymvvm.view.checkout.order.checkout.CheckoutMealFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private lateinit var cartDao: CartDao
    private lateinit var cartViewModel: CategoryViewModel
    private lateinit var cartList:ArrayList<Cart>
    private lateinit var adapter :CartFragmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartDao = CartDao(view.context)
        cartList = cartDao.getAllCartMeal()
        var total = 0.0
        for (i in 0 until cartList.size) {
            val cartMeal = cartList[i]
            total += cartMeal.mealPrice * cartMeal.count
        }
        view.findViewById<TextView>(R.id.txt_total_price_value).text =
            total.toString()
        adapter = CartFragmentAdapter(view.context, cartList)
        binding.rvCartItem.layoutManager = LinearLayoutManager(view.context)
        binding.rvCartItem.adapter = adapter

        val btnPlaceOrder = binding.btnPlaceOrder
        btnPlaceOrder.setOnClickListener{ p0 ->
            val activity = p0!!.context as AppCompatActivity
            val checkOutMealFragment = CheckoutMealFragment()
            val bundle = Bundle()
            checkOutMealFragment.arguments = bundle
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_main, checkOutMealFragment)
                .addToBackStack(null)
                .commit()
        }

    }
    companion object{
        const val CART_ID = "cartId"
    }

}