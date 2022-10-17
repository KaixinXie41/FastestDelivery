package com.example.secondprojectbymvvm.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivityPhoneBinding
import com.example.secondprojectbymvvm.model.local.entities.Phone
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class RegistrationPhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding
    private var number:String = ""
    private var countryCode:String =""
    private lateinit var storeVerificationId:String
    private lateinit var resendToken:PhoneAuthProvider.ForceResendingToken
    private lateinit var  callBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.btnSendCode.setOnClickListener {
            register()
        }
        setUpCallBack()
        initView()

    }
    private fun initView() {
        val spinner = binding.spinnerCountryCode

        val arrayList = ArrayList<Phone>()
        arrayList.apply {
            add(Phone("+1", R.drawable.us))
            add(Phone("+86", R.drawable.china))
            add(Phone("+44", R.drawable.uk))
            add(Phone("+49", R.drawable.germany))
            add(Phone("+33", R.drawable.france))
            add(Phone("+81", R.drawable.japan))
            add(Phone("+91", R.drawable.india))
            add(Phone("+39", R.drawable.italy))
            add(Phone("+1", R.drawable.canada))
        }
        val userAdapter = RegistrationPhoneAdapter(arrayList)
        spinner.adapter = userAdapter
        binding.spinnerCountryCode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                countryCode = arrayList[position].areaCode

            }
        }
    }


    private fun setUpCallBack() {
        callBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.i("tag", "onVerificationCompleted")
                finish()

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.i("tag", "onVerificationFailed")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                storeVerificationId = p0
                resendToken = p1
                val intent = Intent(this@RegistrationPhoneActivity, RegistrationConfirmOTPActivity::class.java)
                intent.putExtra("storedVerificationId", storeVerificationId)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun register(){
        number = binding.edtPhone.text.toString()
        if(number.isNotEmpty() && countryCode.isNotEmpty()){
            number = "$countryCode$number"
            sendVerification(number)
        }
    }

    private fun sendVerification(number: String){

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callBack)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}