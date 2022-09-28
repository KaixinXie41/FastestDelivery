package com.example.secondprojectbymvvm.view.checkout.order.checkout

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCheckoutSummaryBinding
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.order.checkout.CheckoutDeliveryFragment.Companion.ADDRESS
import com.example.secondprojectbymvvm.view.checkout.order.checkout.CheckoutDeliveryFragment.Companion.ADDRESS_TITLE
import com.example.secondprojectbymvvm.view.checkout.order.checkout.CheckoutPaymentFragment.Companion.PAYMENT
import com.example.secondprojectbymvvm.model.data.order.place.Item
import com.google.android.material.progressindicator.CircularProgressIndicator

class CheckoutSummaryFragment : Fragment() {

    private lateinit var adapter: CheckoutCartMealAdapter
    private lateinit var cartProductList: ArrayList<Cart>
    private lateinit var cartDao: CartDao
    private lateinit var currentView: View
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var binding: FragmentCheckoutSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartDao = CartDao(view.context)
        cartProductList = cartDao.getAllCartMeal()
        sharedPreferences = this.requireActivity().getSharedPreferences(
            LoginActivity.Account_Information,
            AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()

        var total = 0.0
        for (i in 0 until cartProductList.size) {
            val cartProduct = cartProductList[i]
            total += cartProduct.mealPrice * cartProduct.count
        }
        view.findViewById<TextView>(R.id.txt_summary_total_bill_amount_value).text =
            total.toString()

        adapter = CheckoutCartMealAdapter(view.context, cartProductList)
        binding.recyclerViewSummaryCart.layoutManager =
            LinearLayoutManager(view.context)
        binding.recyclerViewSummaryCart.adapter = adapter

        val payment = sharedPreferences.getString(PAYMENT, "None")
        val title = sharedPreferences.getString(ADDRESS_TITLE, "None")
        val address = sharedPreferences.getString(ADDRESS, "None")

        if (payment != null) {
            view.findViewById<TextView>(R.id.txt_payment_info).text = payment
        }
        if (address != null && title != null) {
            view.findViewById<TextView>(R.id.txt_summary_address_title).text = title
            view.findViewById<TextView>(R.id.txt_summary_address).text = address
        }

        val btnPlaceOrder: Button = view.findViewById(R.id.btn_summary_confirm_place)
        btnPlaceOrder.setOnClickListener {
            val userId = sharedPreferences.getString(LoginActivity.USER_ID, "-1")
            userId?.let {
                val cartMealList = ArrayList<Cart>()
                val itemList = ArrayList<Item>()
                var summaryTotal = 0
                for (i in 0 until cartMealList.size) {
                    val cart = cartMealList[i]
                    summaryTotal += (cart.mealPrice * cart.count).toInt()
                    itemList.add(
                        Item(
                            cart.cartId!!.toInt(),
                            cart.count,
                            cart.mealPrice.toInt()
                        )
                    )
                }
            }
        }
    }

    /*
    override fun setResult(message: String?) {
        cartDao.clearTable()
    }*/
}