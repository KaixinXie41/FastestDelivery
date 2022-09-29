package com.example.secondprojectbymvvm.view.checkout.deliveryoption

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentDeliveryBinding
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.model.local.address.AddressDao
import com.example.secondprojectbymvvm.viewmodel.AddressViewModel

class DeliveryFragment : Fragment() {

    private lateinit var binding : FragmentDeliveryBinding
    private lateinit var addressViewModel: AddressViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeliveryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        binding.btnSaveAddress.setOnClickListener {
            saveAddress()
        }
    }

    private fun setUpViewModel() {
        addressViewModel = ViewModelProvider(this)[AddressViewModel::class.java]
    }

    private fun saveAddress() {
        binding.apply {
            addressViewModel.add(Address(0,
                edtEnterAddress.text.toString(),
                edtEnterTitle.text.toString(),
                ))
            edtEnterAddress.text?.clear()
            edtEnterTitle.text?.clear()
        }
    }
}