package com.example.secondprojectbymvvm.view.checkout.deliveryoption

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentPickupBinding
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.model.local.restaurant.Restaurant
import com.example.secondprojectbymvvm.model.local.restaurant.RestaurantDao
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.PickUpAdapter.Companion.RESTAURANT_ID
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class PickupFragment : Fragment() {

    private lateinit var binding : FragmentPickupBinding
    private lateinit var restaurantDao: RestaurantDao
    private lateinit var restaurantViewModel: CheckoutViewModel
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPickupBinding.inflate(inflater, container,false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        restaurantDao=appDatabase.getRestaurantDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpObserver()
        initView()
    }

    private fun initView() {
        restaurantDao.insert(Restaurant(1,"Waffle House","1432 N Custer Rd","4"))
        restaurantDao.insert(Restaurant(2,"IHOP","1432 E Park Rd","4"))
        restaurantDao.insert(Restaurant(3,"Chili's Grill","4132 W Legacy Rd","4.5"))
        restaurantDao.insert(Restaurant(4,"Arby's","2413 S Coit Rd","3"))
        restaurantDao.insert(Restaurant(5,"Pizza Hut","1432 N Alma Rd","3.5"))


    }

    private fun setUpObserver() {
        restaurantViewModel.allRestaurant.observe(viewLifecycleOwner){
            binding.rvRestaurantList.adapter = PickUpAdapter(
                restaurantViewModel, it, this.requireContext())
        }
    }

    private fun setUpViewModel() {
        restaurantViewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        restaurantViewModel.getRestaurantByRestaurantId(arguments?.getInt(RESTAURANT_ID)?: 0)
    }

    private fun setUpView() {
        binding.apply {
            rvRestaurantList.layoutManager = LinearLayoutManager(context)

        }
    }

}