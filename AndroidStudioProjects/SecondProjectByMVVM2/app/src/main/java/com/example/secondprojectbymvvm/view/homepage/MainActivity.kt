package com.example.secondprojectbymvvm.view.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivityMainBinding
import com.example.secondprojectbymvvm.view.checkout.CartFragment
import com.example.secondprojectbymvvm.view.checkout.order.OrderFragment
import com.example.secondprojectbymvvm.view.homepage.search.SearchByAreaFragment
import com.example.secondprojectbymvvm.view.homepage.search.SearchByIngredientFragment
import com.example.secondprojectbymvvm.view.homepage.search.SearchFragment
import com.example.secondprojectbymvvm.viewmodel.AuthViewModel
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var viewModel:AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(HomePageFragment(), R.id.frameLayout_main)
        addFragment(SearchFragment(), R.id.frameLayout_search)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        initView()
    }

    private fun initView() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        drawerLayout = findViewById(R.id.drawerLayout_main)

        val navigationView = binding.navView as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItems ->
            when (menuItems.itemId) {
                R.id.nav_cart_page -> {
                    val cartFragment = CartFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, cartFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.nav_home_page -> {
                    val homePageFragment = HomePageFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, homePageFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.nav_order_page -> {
                    val orderFragment = OrderFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, orderFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
            true
        }


        binding.btnOption.shrink()
        var isCollasped = false
        binding.apply {
            btnOption.setOnClickListener{
                if(!isCollasped){
                    btnOption.show()
                    btnOptionArea.show()
                    btnOptionIngredient.show()
                    isCollasped = true
                }else{
                    btnOptionArea.hide()
                    btnOptionIngredient.hide()
                    isCollasped = false
                }
            }
            btnOptionArea.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val searchByAreaFragment = SearchByAreaFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_search, searchByAreaFragment)
                    .addToBackStack(null)
                    .commit()
            }
            btnOptionIngredient.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val searchByIngredientsFragment = SearchByIngredientFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_search, searchByIngredientsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayoutMain.isDrawerOpen(GravityCompat.START))
                binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
            else {
                binding.drawerLayoutMain.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun addFragment(fragment: HomePageFragment, container: Int) {
        supportFragmentManager.beginTransaction()
            .add(container, fragment)
            .commit()
    }

    private fun addFragment(fragment: SearchFragment, container: Int) {
        supportFragmentManager.beginTransaction()
            .add(container, fragment)
            .commit()
    }

}