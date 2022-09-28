package com.example.secondprojectbymvvm.view.checkout.order.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.databinding.ItemViewPurchaseMealBinding
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao

class CheckoutCartMealAdapter(
    private val context: Context,
    val cartArrayList:ArrayList<Cart>
) : RecyclerView.Adapter<CheckoutCartMealAdapter.CartProductViewHolder>(){

    private lateinit var binding: ItemViewPurchaseMealBinding
    private lateinit var cartDao:CartDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        binding = ItemViewPurchaseMealBinding.inflate(layoutInflater, parent, false)
        cartDao = CartDao(context)
        return CartProductViewHolder(binding.root)
    }

    override fun getItemCount() = cartArrayList.size

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.apply {
            val list = cartArrayList[position]
            cartMealName.text = list.mealName
            unitPrice.text = "${list.mealPrice}"
            unitQuantity.text = list.count.toString()
            productTotalPrice.text = (list.count *list.mealPrice).toString()
        }
    }

    inner class CartProductViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val cartMealName: TextView = binding.txtMealName
        val unitPrice: TextView = binding.txtUnitPriceValue
        val unitQuantity: TextView = binding.txtQuantityValue
        val productTotalPrice : TextView = binding.txtTotalPriceValue
    }
}