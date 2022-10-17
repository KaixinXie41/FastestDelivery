package com.example.secondprojectbymvvm.view.checkout.checkout

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCheckoutPaymentQRCodeBinding
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.CartFragmentAdapter.Companion.TOTAL_PRICE

class CheckoutPaymentQRCodeFragment : Fragment() {

    private lateinit var binding : FragmentCheckoutPaymentQRCodeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutPaymentQRCodeBinding.inflate(inflater, container, false)
        sharedPreferences = this.requireActivity().getSharedPreferences(
            LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val total = sharedPreferences.getString(TOTAL_PRICE,"None")
        binding.txtTotalAmountPaypal.text = total.toString()
        binding.imgQRCode.setOnClickListener {
            val url = "https://www.paypal.com/us/home"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

}