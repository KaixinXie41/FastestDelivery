package com.example.secondprojectbymvvm.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.secondprojectbymvvm.databinding.ActivityComfirmOtpactivityBinding
import com.example.secondprojectbymvvm.view.homepage.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class RegistrationConfirmOTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComfirmOtpactivityBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComfirmOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val storedVerification = intent.getStringExtra("storedVerificationId")

        binding.btnConfirm.setOnClickListener() {
            val otp = binding.edtOTP.text.toString()
            if (otp.isNotEmpty()) {
                val credential: PhoneAuthCredential =
                    PhoneAuthProvider.getCredential(storedVerification.toString(), otp)
                signInWithCredential(credential)
                val intent = Intent(this@RegistrationConfirmOTPActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }

    }
}