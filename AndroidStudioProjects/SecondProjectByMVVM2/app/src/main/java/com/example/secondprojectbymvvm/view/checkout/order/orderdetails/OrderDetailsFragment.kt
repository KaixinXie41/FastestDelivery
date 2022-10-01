package com.example.secondprojectbymvvm.view.checkout.order.orderdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentOrderDetailsBinding
import com.example.secondprojectbymvvm.model.local.address.AppDatabase


class OrderDetailsFragment : Fragment() {

    private lateinit var binding : FragmentOrderDetailsBinding
    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}