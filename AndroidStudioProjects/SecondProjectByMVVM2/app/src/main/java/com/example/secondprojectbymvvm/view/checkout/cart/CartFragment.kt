package com.example.secondprojectbymvvm.view.checkout.cart

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCartBinding
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.entities.Cart
import com.example.secondprojectbymvvm.model.local.dao.CartDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.cart.CartFragmentAdapter.Companion.TOTAL_PRICE
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutMealFragment
import com.example.secondprojectbymvvm.view.mealitemlist.meallist.MealListAdapter.Companion.MEAL_ID
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private lateinit var cartDao: CartDao
    private lateinit var cartViewModel: CheckoutViewModel
    private lateinit var adapter : CartFragmentAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        cartDao = appDatabase.getCartDao()
        sharedPreferences = this.requireActivity().getSharedPreferences(
            LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewHolder()
        serUpObserver()
        val btnPlaceOrder = binding.btnPlaceOrder
        btnPlaceOrder.setOnClickListener{ p0 ->
            val activity = p0!!.context as AppCompatActivity
            val checkOutMealFragment = CheckoutMealFragment()
            val total = binding.txtTotalPriceValue.text.toString()
            val bundle = Bundle()
            bundle.putString(TOTAL_PRICE,total)
            checkOutMealFragment.arguments = bundle
            activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout_full, checkOutMealFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun serUpObserver() {
        cartViewModel.allCart.observe(viewLifecycleOwner) {
            binding.rvCartItem.adapter = CartFragmentAdapter(
                cartViewModel, it as MutableList<Cart>, this.requireContext()
            )
            val totalAmount = sharedPreferences.getString(TOTAL_PRICE,"")
            binding.txtTotalPriceValue.text = totalAmount
            var total = 0.0
            val size = it.size
            var cartIdNumber = 0
            for (i in 0 until size) {
                val meal = it[i]
                total += meal.mealPrice * meal.count
                cartIdNumber = meal.cartId.toInt()
            }
            binding.txtTotalPriceValue.text = total.toString()
            editor.putString(TOTAL_PRICE, total.toString())
            editor.putInt(CART_ID, cartIdNumber)
            editor.apply()
        }
        cartViewModel.totalAmount.observe(viewLifecycleOwner){
            binding.txtTotalPriceValue.text = it.toString()
        }
    }

    private fun setUpViewHolder() {
        cartViewModel = ViewModelProvider(this@CartFragment)[CheckoutViewModel::class.java]
        cartViewModel.getCartByMealId(arguments?.getString(MEAL_ID)?:"")
    }

    private fun setUpView() {
        binding.apply {
            rvCartItem.layoutManager = LinearLayoutManager(context)
        }
    }

    companion object{
        const val CART_ID = "cartId"
        const val CART = "cart"
    }

}