package com.example.secondprojectbymvvm.view.checkout.deliveryoption

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentEditAddressBinding
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.model.local.address.AddressDao
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment
import com.example.secondprojectbymvvm.view.checkout.deliveryoption.DeliveryAdapter.Companion.ADDRESS_ID
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class EditAddressFragment : Fragment() {

    private lateinit var binding:FragmentEditAddressBinding
    private lateinit var addressViewModel : CheckoutViewModel
    private lateinit var addressDao: AddressDao
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditAddressBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        addressDao = appDatabase.getAddressDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {
        addressViewModel.allAddress.observe(viewLifecycleOwner) {
            binding.apply {
                val addressId = it[0].addressId
                btnUpdate.setOnClickListener {
                    val title = edtAddressTitle.text.toString()
                    val address = edtAddress.text.toString()
                    addressViewModel.updateAddress(Address(addressId,address,title))
                    val activity = it!!.context as AppCompatActivity
                    val checkoutDeliveryFragment = CheckoutDeliveryFragment()
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_delivery_option, checkoutDeliveryFragment)
                        .addToBackStack(null)
                        .commit()
                }
                btnCancel.setOnClickListener {
                    val activity = it!!.context as AppCompatActivity
                    val checkoutDeliveryFragment = CheckoutDeliveryFragment()
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_delivery_option, checkoutDeliveryFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }


    private fun setUpViewModel() {
        addressViewModel = ViewModelProvider(this@EditAddressFragment)[CheckoutViewModel::class.java]
        addressViewModel.getAddressByAddressId(arguments?.getInt(ADDRESS_ID)?: 0)
    }

}