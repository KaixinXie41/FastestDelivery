package com.example.secondprojectbymvvm.view.checkout.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentOrderBinding
import com.example.secondprojectbymvvm.view.checkout.order.orderdetails.OrderDetailsFragment
import com.example.secondprojectbymvvm.viewmodel.OrderViewModel


class OrderFragment : Fragment() {

    private lateinit var binding : FragmentOrderBinding
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
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
        binding.txtOrderTitle.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val orderDetailsFragment = OrderDetailsFragment()
                val bundle = Bundle()
                orderDetailsFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_main, orderDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

    private fun setUpObserver() {
        orderViewModel.allOrder.observe(viewLifecycleOwner){
            binding.rvOrderList.adapter = OrderAdapter(
                orderViewModel, it ,this)
        }
    }

    private fun setUpView() {
        binding.apply {
            rvOrderList.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpViewModel() {
        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]
    }
}