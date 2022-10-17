package com.example.secondprojectbymvvm.view.checkout.checkout

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCheckoutDeliveryBinding
import com.example.secondprojectbymvvm.model.local.dao.AddressDao
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.RestaurantDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.AddAddressFragment
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.DeliveryFragment
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.PickupFragment
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class CheckoutDeliveryFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutDeliveryBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var addressDao: AddressDao
    private lateinit var addressViewModel : CheckoutViewModel
    private lateinit var restaurantDao: RestaurantDao
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutDeliveryBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        addressDao = appDatabase.getAddressDao()
        restaurantDao = appDatabase.getRestaurantDao()
        sharedPreferences = this.requireActivity().getSharedPreferences(
            LoginActivity.Account_Information,
            AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        initView()
        binding.btnDeliveryNext.setOnClickListener {
            (this.parentFragment as CheckoutMealFragment).nextPager()
        }
    }

    private fun setUpViewModel() {
        addressViewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            rbPickUp.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val pickupFragment = PickupFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_delivery_option, pickupFragment)
                    .addToBackStack(null)
                    .commit()
                val deliveryType = rbDelivery.text.toString()
                editor.putString(Delivery_TYPE,deliveryType)
                editor.apply()
            }
            rbDelivery.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val deliveryFragment = DeliveryFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_delivery_option, deliveryFragment)
                    .addToBackStack(null)
                    .commit()
                val deliveryType = rbDelivery.text.toString()
                editor.putString(Delivery_TYPE,deliveryType)
                editor.apply()
            }
            binding.btnAddAddress.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val addAddressFragment = AddAddressFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_delivery_option, addAddressFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    companion object {
        const val ADDRESS_TITLE = "address_title"
        const val ADDRESS = "address_address"
        const val Delivery_TYPE = "delivery_type"
    }
}

