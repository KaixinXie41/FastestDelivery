package com.example.secondprojectbymvvm.view.checkout.deliveryoption

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentAddAddressBinding
import com.example.secondprojectbymvvm.model.local.entities.Address
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel


class AddAddressFragment : Fragment() {

    private lateinit var binding:FragmentAddAddressBinding
    private lateinit var addressViewModel : CheckoutViewModel

    private lateinit var addressItem: Address
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAddressBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        binding.btnSave.setOnClickListener {
            saveAddress()
            val activity = it!!.context as AppCompatActivity
            val checkoutDeliveryFragment = CheckoutDeliveryFragment()
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_full, checkoutDeliveryFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.btnCancel.setOnClickListener{
            val activity = it!!.context as AppCompatActivity
            val checkoutDeliveryFragment = CheckoutDeliveryFragment()
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_full, checkoutDeliveryFragment)
                .addToBackStack(null)
                .commit()

        }
    }

    private fun setUpViewModel() {
        addressViewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
    }

    private fun saveAddress() {
        binding.apply {
            val title = edtAddressTitle.text.toString()
            val address = edtAddress.text.toString()
            addressViewModel.addAddress(Address(0,address, title))
            edtAddressTitle.text?.clear()
            edtAddress.text?.clear()
        }
    }
}