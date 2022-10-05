package com.example.secondprojectbymvvm.view.checkout.deliveryoption

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.databinding.ItemViewRestaurantBinding
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.model.local.restaurant.Restaurant
import com.example.secondprojectbymvvm.model.local.restaurant.RestaurantDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutSummaryFragment
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel


class PickUpAdapter(
    private val viewModel : CheckoutViewModel,
    private val restaurantList: List<Restaurant>,
    private val context: Context)
    :RecyclerView.Adapter<PickUpAdapter.RestaurantViewHolder>(){

    private lateinit var binding : ItemViewRestaurantBinding
    private lateinit var appDatabase:AppDatabase
    private lateinit var restaurantDao: RestaurantDao
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        binding = ItemViewRestaurantBinding.inflate(layoutInflater, parent, false)
        appDatabase = AppDatabase.getInstance(context)
        restaurantDao = appDatabase.getRestaurantDao()
        sharedPreferences = context.getSharedPreferences(LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return RestaurantViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.apply {
            val restaurant = restaurantList[position]
            resName.text = restaurant.res_name
            resAddress.text = restaurant.res_address
            resRating.rating= restaurant.res_rating.toFloat()

            itemView.setOnClickListener {
                val pickUpFragment = PickupFragment()
                val checkoutSummaryFragment = CheckoutSummaryFragment()
                val bundle = Bundle()
                val restaurantId = restaurant.resId
                bundle.putInt(RESTAURANT_ID, restaurantId)
                pickUpFragment.arguments = bundle
                checkoutSummaryFragment.arguments = bundle
            }
            restaurantRadioButton.setOnClickListener {
                val address = restaurant.res_address
                val title = restaurant.res_name
                val latitude = restaurant.latitude.toString()
                val longitude = restaurant.longitude.toString()
                editor.putString(CheckoutDeliveryFragment.ADDRESS, address)
                editor.putString(CheckoutDeliveryFragment.ADDRESS_TITLE, title)
                editor.putString(LATITUDE, latitude)
                editor.putString(LONGITUDE, longitude)
                editor.apply()
            }
        }
    }

    override fun getItemCount() = restaurantList.size



    inner class RestaurantViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val resName = binding.txtRestaurantName
        val resAddress = binding.txtRestaurantAddress
        val resRating = binding.ratingBarRestaurant
        val restaurantRadioButton = binding.rbPickUp

    }

    companion object{
        const val RESTAURANT_ID = "restaurantId"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }
}