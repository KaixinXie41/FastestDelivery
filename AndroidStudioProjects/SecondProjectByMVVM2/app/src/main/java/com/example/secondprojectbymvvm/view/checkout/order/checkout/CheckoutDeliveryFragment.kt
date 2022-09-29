package com.example.secondprojectbymvvm.view.checkout.order.checkout

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.AddAddressBinding
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.Account_Information
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.USER_ID

class CheckoutDeliveryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout_delivery, container, false)
    }


    companion object {
        const val ADDRESS_TITLE = "address_title"
        const val ADDRESS = "address_address"
    }
}

