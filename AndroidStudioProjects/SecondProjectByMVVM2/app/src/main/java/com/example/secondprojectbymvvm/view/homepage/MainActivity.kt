package com.example.secondprojectbymvvm.view.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(HomePageFragment(), R.id.frameLayout_main)
    }

    private fun addFragment(fragment:HomePageFragment, container:Int) {
        supportFragmentManager.beginTransaction()
            .add(container,fragment)
            .commit()
    }
}