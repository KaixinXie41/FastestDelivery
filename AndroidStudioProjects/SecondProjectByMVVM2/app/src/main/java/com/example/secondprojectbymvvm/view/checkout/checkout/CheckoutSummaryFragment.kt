package com.example.secondprojectbymvvm.view.checkout.checkout

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentCheckoutSummaryBinding
import com.example.secondprojectbymvvm.model.data.order.Item
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.model.data.order.OrderDao
import com.example.secondprojectbymvvm.model.local.cart.Cart
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment.Companion.ADDRESS
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment.Companion.ADDRESS_TITLE
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment.Companion.Delivery_TYPE
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutPaymentFragment.Companion.PAYMENT
import com.example.secondprojectbymvvm.view.homepage.home.MainActivity
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutSummaryFragment : Fragment() {

    private lateinit var adapter: CheckoutCartMealAdapter
    private lateinit var cartMealList: List<Cart>
    private lateinit var cartDao: CartDao
    private lateinit var orderDao:OrderDao
    private lateinit var checkoutViewModel: CheckoutViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var binding: FragmentCheckoutSummaryBinding
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutSummaryBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        cartDao = appDatabase.getCartDao()
        orderDao = appDatabase.getOrderDao()
        sharedPreferences = this.requireActivity().getSharedPreferences(
            LoginActivity.Account_Information,
            AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
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
        val payment = sharedPreferences.getString(PAYMENT,"NONE")
        val title = sharedPreferences.getString(ADDRESS_TITLE,"NONE")
        val address = sharedPreferences.getString(ADDRESS,"NONE")
        val deliveryType = sharedPreferences.getString(Delivery_TYPE, "NONE")

        if(payment!=null){
            binding.txtPaymentInfo.text = payment
        }
        if(address!= null && title != null){
            binding.txtSummaryAddressTitle.text = title
            binding.txtSummaryAddress.text = address
        }
        if(deliveryType != null){
            binding.txtPickUpOption.text = deliveryType
        }

    }



    private fun setUpObserver() {
        checkoutViewModel.allCart.observe(viewLifecycleOwner){ it ->
            binding.recyclerViewSummaryCart.adapter = CheckoutCartMealAdapter(
                checkoutViewModel, it,this.requireContext()
            )
            var total = 0.0
            val size = it.size
            for (i in 0 until size) {
                val meal = it[i]
                total += meal.totalPrice * meal.count
            }
            binding.txtSummaryTotalBillAmountValue.text = total.toString()
            val totalAmount = binding.txtSummaryTotalBillAmountValue.text.toString()
            val payment = sharedPreferences.getString(PAYMENT,"NONE")
            val title = sharedPreferences.getString(ADDRESS_TITLE,"NONE")
            val address = sharedPreferences.getString(ADDRESS,"NONE")
            val deliveryType = sharedPreferences.getString(Delivery_TYPE, "NONE")
            binding.btnSummaryConfirmPlace.setOnClickListener {
                val userId = sharedPreferences.getInt(LoginActivity.USER_ID, -1)
                userId.let {
                    val cartMealList = ArrayList<Cart>()
                    val itemList = ArrayList<Item>()
                    for(i in 0 until cartMealList.size){
                        val cartMeal = cartMealList[i]
                        itemList.add(Item(
                            cartMeal.cartId!!.toInt(),
                            cartMeal.count,
                            cartMeal.mealPrice.toInt()
                        ))
                    }
                    if(payment !=null && address!=null && title!=null){
                        val date = Calendar.getInstance().time
                        val formatter = SimpleDateFormat.getDateTimeInstance()
                        val orderDate = formatter.format(date)
                        orderDao.insert(
                            Order(0, address,title,totalAmount, orderDate.toString(),"out of delivery",payment,deliveryType.toString())
                        )
                        cartDao.delete()
                    }
                }
                val intent = Intent(this.requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setUpViewModel() {
        checkoutViewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
    }

    private fun setUpView() {
        binding.apply {
            recyclerViewSummaryCart.layoutManager = LinearLayoutManager(context)
        }
    }
    companion object{
        const val BILL_TOTAL = "billTotal"
    }
}