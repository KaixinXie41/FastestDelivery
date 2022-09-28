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
import androidx.appcompat.app.AppCompatActivity
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.model.local.cart.CartDao

class CheckoutPaymentFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var cartDao: CartDao
    private lateinit var currentView:View
    private lateinit var payment:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout_payment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view
        cartDao = CartDao(view.context)
        val group = view.findViewById<RadioGroup>(R.id.radio_group_payment)
        group.setOnCheckedChangeListener { _: RadioGroup,
                                           _: Int ->
            val checkRadioButton = group.findViewById<RadioButton>(group.checkedRadioButtonId)
            checkRadioButton?.let {
                payment = when (checkRadioButton.id) {
                    R.id.rtn_cod -> {
                        "Cash on Delivery"
                    }
                    R.id.rtn_dc_cc -> {
                        "Debit Card or Credit Card"
                    }
                    R.id.rtn_oba -> {
                        "Online Bank Account"
                    }
                    R.id.rtn_paypal -> {
                        "Paypal"
                    }
                    else -> {
                        "Cash on Delivery"
                    }
                }
            }
            val btnNextStep: Button = currentView.findViewById(R.id.payment_btnNext)
            btnNextStep.setOnClickListener{
                editor.putString(PAYMENT, payment)
                editor.apply()
                (this.parentFragment as CheckoutMealFragment).nextPager()
            }
        }
    }
    companion object{
        const val PAYMENT ="payment"
    }

}