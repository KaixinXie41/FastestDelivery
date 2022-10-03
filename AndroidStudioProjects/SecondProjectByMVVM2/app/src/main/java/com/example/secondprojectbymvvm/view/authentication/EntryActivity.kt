package com.example.secondprojectbymvvm.view.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.secondprojectbymvvm.databinding.ActivityEntryBinding
import com.example.secondprojectbymvvm.view.homepage.MainActivity

class EntryActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnRegister.setOnClickListener {
                val intent = Intent(this@EntryActivity, RegistrationActivity::class.java)
                startActivity(intent)
            }

            btnLogin.setOnClickListener {
                val intent = Intent(this@EntryActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            txtGuestEntry.setOnClickListener{
                val intent = Intent(this@EntryActivity, MainActivity::class.java)
                startActivity(intent)
                makeToast(this@EntryActivity, "Login as Guest ")
            }
        }
    }

    private fun makeToast(context: Context, s:String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}