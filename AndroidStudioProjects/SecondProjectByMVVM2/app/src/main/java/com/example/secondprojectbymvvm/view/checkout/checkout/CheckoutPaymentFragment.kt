package com.example.secondprojectbymvvm.view.checkout.checkout

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentCheckoutPaymentBinding
import com.example.secondprojectbymvvm.model.local.address.AppDatabase
import com.example.secondprojectbymvvm.model.local.cart.CartDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity

class CheckoutPaymentFragment : Fragment() {

    private lateinit var binding : FragmentCheckoutPaymentBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var cartDao: CartDao
    private lateinit var payment:String
    private lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutPaymentBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getInstance(this.requireContext())
        cartDao = appDatabase.getCartDao()
        sharedPreferences = this.requireActivity().getSharedPreferences(
            LoginActivity.Account_Information,
            AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        payment = "cod"
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val group = binding.radioGroupPayment
        group.setOnCheckedChangeListener { _: RadioGroup,
                                           _: Int ->
            val checkRadioButton = group.findViewById<RadioButton>(group.checkedRadioButtonId)
            checkRadioButton?.let {
                when (checkRadioButton.id) {
                    R.id.rtn_cod -> {
                        editor.putString(PAYMENT,"Cash on Delivery")
                        editor.apply()
                    }
                    R.id.rtn_paypal -> {
                        editor.putString(PAYMENT,"Paypal" )
                        editor.apply()
                    }
                    R.id.rtn_dc_cc -> {
                        editor.putString(PAYMENT,"Debit Card or Credit Card" )
                        editor.apply()
                    }
                    R.id.rtn_gift_card -> {
                        editor.putString(PAYMENT,"Gift Card" )
                        editor.apply() }
                    else -> { "Cash on Delivery" }
                }
            }
            binding.paymentBtnNext.setOnClickListener{
                (this.parentFragment as CheckoutMealFragment).nextPager()
            }
        }
    }
    companion object{
        const val PAYMENT ="payment"
    }

}