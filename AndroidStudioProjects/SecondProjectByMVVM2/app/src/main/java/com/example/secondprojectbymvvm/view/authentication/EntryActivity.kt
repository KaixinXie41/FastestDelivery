package com.example.secondprojectbymvvm.view.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.secondprojectbymvvm.databinding.ActivityEntryBinding
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.USER_ID
import com.example.secondprojectbymvvm.view.homepage.home.MainActivity

class EntryActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEntryBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = this.getSharedPreferences(LoginActivity.Account_Information, MODE_PRIVATE)

        editor = sharedPreferences.edit()
        initView()
    }

    private fun initView() {
        val userId = sharedPreferences.getInt(USER_ID, -1)
        if(userId != -1){
            val intent = Intent(this@EntryActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.apply {
            btnRegister.setOnClickListener {
                val intent = Intent(this@EntryActivity, RegistrationActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnLogin.setOnClickListener {
                val intent = Intent(this@EntryActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            txtGuestEntry.setOnClickListener{
                val intent = Intent(this@EntryActivity, MainActivity::class.java)
                startActivity(intent)
                makeToast(this@EntryActivity, "Login as Guest ")
                finish()
            }
        }
    }

    private fun makeToast(context: Context, s:String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}