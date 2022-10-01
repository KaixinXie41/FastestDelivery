package com.example.secondprojectbymvvm.view.mealitemlist.mealdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentMealDetailsBinding
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.view.mealitemlist.MealListAdapter.Companion.MEAL_ID
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel
import kotlin.random.Random

class MealDetailsFragment : Fragment() {

    private lateinit var binding:FragmentMealDetailsBinding
    private lateinit var mealViewModel : CategoryViewModel
    private lateinit var currentView : View
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao:CartDao
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        cartDao = appDatabase.getCartDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpView() {
        binding.apply {
            rvMealDetails.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpViewModel() {
        mealViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        mealViewModel.searchByMealId(arguments?.getString(MEAL_ID) ?: "" )
    }

    private fun setUpObserver(){
        mealViewModel.mealLiveData.observe(viewLifecycleOwner){
            binding.rvMealDetails.adapter = MealDetailsAdapter(mealViewModel, it.meals ,this)
            val price = price()
            val total = (count * price).toDouble()
            val cartItem = Cart(null, it.meals[0].strMeal, it.meals[0].idMeal,price.toDouble(),count,total,it.meals[0].strMealThumb )
            binding.btnPlus.setOnClickListener {
                count++
                binding.txtMealCount.text = count.toString()
                binding.txtTotalPrice.text = (count * price).toString()
            }
            binding.btnMin.setOnClickListener {
                if(count > 0){
                    count--
                }
                binding.txtMealCount.text = count.toString()
                binding.txtTotalPrice.text = (count * price).toString()


            }
            binding.btnAddToCart.setOnClickListener{
                cartItem.count = count
                cartItem.totalPrice = total
                cartDao.addCart(cartItem)
            }
        }
    }
    private fun price(): Int {
        return Random.nextInt(15, 40)
    }
}