package com.example.secondprojectbymvvm.view.mealitemlist.mealdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewMealDetailsBinding
import com.example.secondprojectbymvvm.databinding.ManageMealCountBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.view.YoutubeFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class MealDetailsAdapter(
    private var viewModel: CategoryViewModel,
    private var mealArrayList: ArrayList<Meal>,
    private var context: MealDetailsFragment
) : RecyclerView.Adapter<MealDetailsAdapter.MealDetailsHolder>() {

    private lateinit var binding: ItemViewMealDetailsBinding
    private lateinit var cartDao: CartDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewMealDetailsBinding.inflate(layoutInflater, parent,false)
        cartDao = CartDao(parent.context)
        return MealDetailsHolder(binding.root)
    }

    override fun getItemCount() = mealArrayList.size


    override fun onBindViewHolder(holder: MealDetailsHolder, position: Int) {
        holder.apply {
            val meal = mealArrayList[position]
            mealDetailsName.text = meal.strMeal
            mealDetailsArea.text = meal.strArea
            mealDetailsDesc.text = meal.strInstructions

            Glide.with(context)
                .load(meal.strMealThumb)
                .into(mealDetailsImg)

            var cart = cartDao.getCartMealByMealId(meal.idMeal.toInt())
            if(cart!= null && cart.count >0) {
                mealDetailsAddToCart.visibility = View.GONE
                binding.layoutAddMealCount.visibility = View.VISIBLE
                mealDetailsCount.text = cart.count.toString()
            }

            mealDetailsBtnSub.setOnClickListener{
                if(cart != null){
                    if(cart.count<2){
                        cart.cartId?.let { item ->
                            if (cartDao.deleteCartMeal(item)) {
                                Log.e(
                                    "Delete",
                                    "Delete cart id= ${cart.cartId} name = ${cart.mealName} success"
                                )
                                notifyItemRemoved(position)
                                mealArrayList.removeAt(position)
                                notifyItemRangeChanged(position, mealArrayList.size)
                            }
                        }
                    }else {
                        cart.count = cart.count - 1
                        cartDao.updateCartMeal(cart)
                        mealDetailsCount.text = cart.count.toString()
                    }
                }
            }
            mealDetailsBtnAdd.setOnClickListener {
                if (cart != null) {
                    cart.count = cart.count + 1
                    cartDao.updateCartMeal(cart)
                    mealDetailsCount.text = cart.count.toString()
                }
            }

            btnYoutube.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val youtubeFragment = YoutubeFragment()
                val bundle = Bundle()
                val url = meal.strYoutube
                bundle.putString(YOUTUBE_URL, url)
                youtubeFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_full, youtubeFragment)
                    .addToBackStack(null)
                    .commit()
            }

            mealDetailsAddToCart.setOnClickListener{
                mealDetailsAddToCart.visibility = View.GONE
                binding.layoutAddMealCount.visibility = View.VISIBLE
                val cartMeal = Cart(
                    null,
                    meal.strMeal,
                    meal.idMeal,
                    15.00,
                    1,
                    meal.strMealThumb
                )
                cartMeal.cartId = cartDao.addCart(cartMeal)
                if(cartMeal.cartId != null && cartMeal.cartId!! >0){
                    mealDetailsCount.text = "1"
                    cart = cartDao.getCartMealByMealId(meal.idMeal.toInt())
                }
            }
        }
    }

    inner class MealDetailsHolder(view:View):RecyclerView.ViewHolder(view) {
        val mealDetailsImg : ImageView = binding.imgMealDetails
        val mealDetailsName: TextView = binding.txtMealDetailsName
        val mealDetailsArea: TextView = binding.txtMealDetailsArea
        val mealDetailsDesc: TextView = binding.txtMealDetailsDesc
        val mealDetailsRating : TextView = binding.txtMealDetailsRating
        val btnYoutube:ImageView = binding.imgYoutube

        val mealDetailsBtnAdd : ImageButton = binding.btnPlus
        val mealDetailsBtnSub : ImageButton = binding.btnMin
        val mealDetailsCount : TextView = binding.txtMealCount
        val mealDetailsAddToCart : TextView = binding.txtAddToCart
    }
    companion object{
        const val YOUTUBE_URL = "url"
    }
}