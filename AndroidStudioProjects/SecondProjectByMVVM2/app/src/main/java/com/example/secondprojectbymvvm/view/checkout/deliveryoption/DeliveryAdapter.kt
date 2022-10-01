package com.example.secondprojectbymvvm.view.checkout.deliveryoption

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewAddressBinding
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.model.local.address.AddressDao
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment.Companion.ADDRESS
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutDeliveryFragment.Companion.ADDRESS_TITLE
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutSummaryFragment
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class DeliveryAdapter(
    private val viewModel: CheckoutViewModel,
    private val addressList:List<Address>,
    private val context: Context)
    :RecyclerView.Adapter<DeliveryAdapter.AddressViewHolder>() {

    private lateinit var binding: ItemViewAddressBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var addressDao: AddressDao
    private lateinit var addressItem: Address
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        binding = ItemViewAddressBinding.inflate(layoutInflater, parent, false)
        appDatabase = AppDatabase.getInstance(context)
        addressDao = appDatabase.getAddressDao()
        sharedPreferences = context.getSharedPreferences(LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return AddressViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.apply {
            val item = addressList[position]
            txtAddressTitle.text = item.title
            txtAddress.text = item.address


            btnEdit.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val editAddressFragment = EditAddressFragment()
                val bundle = Bundle()
                val addressId = item.addressId
                bundle.putInt(ADDRESS_ID, addressId)
                editAddressFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_delivery_option, editAddressFragment)
                    .addToBackStack(null)
                    .commit()
            }
            itemView.setOnClickListener {
                val checkoutSummaryFragment = CheckoutSummaryFragment()
                val deliveryFragment = DeliveryFragment()
                val bundle = Bundle()
                val addressId = item.addressId
                bundle.putInt(ADDRESS_ID, addressId)
                checkoutSummaryFragment.arguments = bundle
                deliveryFragment.arguments = bundle
            }
            addressRadioButton.setOnClickListener {
                val address = item.address
                val title = item.title
                editor.putString(ADDRESS, address)
                editor.putString(ADDRESS_TITLE, title)
                editor.apply()
            }
        }
    }



    override fun getItemCount() = addressList.size

    inner class AddressViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val txtAddressTitle = binding.txtAddressTitle
        val txtAddress = binding.txtAddress
        val btnEdit = binding.btnEditAddress
        val addressRadioButton = binding.radioButton
    }

    companion object{
        const val  ADDRESS_ID = "addressId"
    }
}