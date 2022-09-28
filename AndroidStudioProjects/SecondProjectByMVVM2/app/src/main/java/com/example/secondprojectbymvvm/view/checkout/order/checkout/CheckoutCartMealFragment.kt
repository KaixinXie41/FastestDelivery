package com.example.secondprojectbymvvm.view.checkout.order.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCartBinding
import com.example.secondprojectbymvvm.databinding.FragmentCheckOutMealBinding
import com.example.secondprojectbymvvm.databinding.FragmentCheckoutCartMealBinding
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao

class CheckoutCartMealFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutCartMealBinding
    private lateinit var cartMealList:ArrayList<Cart>
    private lateinit var currentView: View
    private lateinit var cartDao: CartDao
    private lateinit var adapter : CheckoutCartMealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentCheckoutCartMealBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view
        cartDao = CartDao(view.context)
        cartMealList = cartDao.getAllCartMeal()
        var totalAmount = 0.0
        for (i in 0 until cartMealList.size) {
            val cartItem = cartMealList.get(i)
            totalAmount += cartItem.mealPrice * cartItem.count
        }
        view.findViewById<TextView>(R.id.txt_total_amount_value).text =
            totalAmount.toString()
        adapter = CheckoutCartMealAdapter(view.context, cartMealList)
        binding.recyclerViewCheckoutMeal.layoutManager =
            LinearLayoutManager(view.context)
        binding.recyclerViewCheckoutMeal.adapter = adapter

        val btnCheckoutCartItemNext: Button = binding.btnNext
        btnCheckoutCartItemNext.setOnClickListener {
            (this.parentFragment as CheckoutMealFragment).nextPager()
        }

    }

}