package com.example.secondprojectbymvvm.view.checkout.order.orderdetails

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentOrderDetailsBinding
import com.example.secondprojectbymvvm.model.data.order.OrderDao
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.view.checkout.order.OrderAdapter.Companion.ORDER_ID
import com.example.secondprojectbymvvm.view.foodtracking.GetCurrentDeliveryLocationActivity
import com.example.secondprojectbymvvm.view.homepage.HomePageFragment
import com.example.secondprojectbymvvm.view.homepage.MainActivity
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource


class OrderDetailsFragment : Fragment() {

    private lateinit var binding : FragmentOrderDetailsBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var orderDao: OrderDao
    private lateinit var orderViewModel: CheckoutViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpObserver()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpObserver() {
        orderViewModel.allOrder.observe(viewLifecycleOwner){
            binding.recyclerViewOrderDetailsCart.adapter=OrderDetailsAdapter(
                orderViewModel, it, this.requireContext())

            binding.btnGoBackHomePage.setOnClickListener {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)

            }
            binding.btnGetCurrentOrderLocation.setOnClickListener {
                val intent = Intent(this.requireContext(), GetCurrentDeliveryLocationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setUpViewModel() {
        orderViewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        orderViewModel.getOrderByOrderId(arguments?.getInt(ORDER_ID)?:0)
    }

    private fun setUpView() {
        binding.apply {
            recyclerViewOrderDetailsCart.layoutManager = LinearLayoutManager(context)
        }
    }

}
