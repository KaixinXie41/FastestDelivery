package com.example.secondprojectbymvvm.view.checkout.order.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCartBinding
import com.example.secondprojectbymvvm.databinding.FragmentCheckOutMealBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutMealFragment : Fragment() {

    private lateinit var binding : FragmentCheckOutMealBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentCheckOutMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpTabLayout()


    }

    private fun setUpTabLayout() {
        val tabString = arrayOf("Cart Items", "Delivery", "Payment", "Summary")
        TabLayoutMediator(binding.tabLayoutCheckout,binding.viewpageCheckout){ tab, position->
            tab.text = tabString[position]
        }.attach()
    }

    private fun setUpViewPager() {
        val adapter = CheckoutAdapter(this, 4)
        binding.viewpageCheckout.adapter = adapter
    }

    fun nextPager(){
        binding.viewpageCheckout.currentItem= binding.viewpageCheckout.currentItem + 1
    }
}