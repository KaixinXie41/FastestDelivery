package com.example.secondprojectbymvvm.view.checkout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewCartBinding
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.view.mealitemlist.mealdetails.MealDetailsFragment
import com.example.secondprojectbymvvm.view.mealitemlist.MealListAdapter.Companion.MEAL_ID

class CartFragmentAdapter(
    private val context: Context,
    private val cartArrayList: ArrayList<Cart>)
    :RecyclerView.Adapter<CartFragmentAdapter.CartViewHolder>(){

    private lateinit var binding : ItemViewCartBinding
    private lateinit var cartDao: CartDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        binding = ItemViewCartBinding.inflate(layoutInflater, parent, false)
        cartDao = CartDao(context)
        return CartViewHolder(binding.root)
    }

    override fun getItemCount() = cartArrayList.size


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.apply{
            val Info = cartArrayList[position]
            txtMealName.text = Info.mealName
            txtMealPrice.text = Info.mealPrice.toString()

            val cart = cartDao.getCartMealByMealId(Info.mealId.toInt())
            if(cart!= null && cart.count >0) {
                txtCount.text = cart.count.toString()
            }

            btnSub.setOnClickListener{
                if(cart != null){
                    if(cart.count<2){
                        cart.cartId?.let { item ->
                            if (cartDao.deleteCartMeal(item)) {
                                Log.e(
                                    "Delete",
                                    "Delete cart id= ${cart.cartId} name = ${cart.mealName} success"
                                )
                                notifyItemRemoved(position)
                                cartArrayList.removeAt(position)
                                notifyItemRangeChanged(position, cartArrayList.size)
                            }
                        }
                    }else {
                        cart.count = cart.count - 1
                        cartDao.updateCartMeal(cart)
                        txtCount.text = cart.count.toString()
                    }
                }
            }
            btnAdd.setOnClickListener {
                if (cart != null) {
                    cart.count = cart.count + 1
                    cartDao.updateCartMeal(cart)
                    txtCount.text = cart.count.toString()
                }
            }

            itemView.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val mealDetailsFragment = MealDetailsFragment()
                val bundle = Bundle()
                val mealId = cart?.mealId
                val cartId = cart?.cartId
                bundle.putString(MEAL_ID, mealId)
                mealDetailsFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_full, mealDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }

        }
    }

    inner class CartViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val txtMealName = binding.txtMealName
        val txtMealPrice = binding.txtPrice
        val btnAdd = binding.btnPlus
        val btnSub = binding.btnMin
        val txtCount = binding.txtMealCount
    }
}