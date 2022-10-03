package com.example.secondprojectbymvvm.view.homepage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivityMainBinding
import com.example.secondprojectbymvvm.view.authentication.EntryActivity
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.USER_ID
import com.example.secondprojectbymvvm.view.authentication.ProfileActivity
import com.example.secondprojectbymvvm.view.checkout.CartFragment
import com.example.secondprojectbymvvm.view.checkout.order.OrderFragment
import com.example.secondprojectbymvvm.view.foodtracking.MapsActivity
import com.example.secondprojectbymvvm.view.homepage.search.SearchByAreaFragment
import com.example.secondprojectbymvvm.view.homepage.search.SearchByIngredientFragment
import com.example.secondprojectbymvvm.view.homepage.search.SearchFragment
import com.example.secondprojectbymvvm.viewmodel.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var viewModel:AuthViewModel
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

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
        sharedPreferences = getSharedPreferences(LoginActivity.Account_Information, MODE_PRIVATE)
        editor = sharedPreferences.edit()


        val navHeader = binding.navView.inflateHeaderView(R.layout.nav_header)
        val userName :TextView = navHeader.findViewById(R.id.txt_nv_head_name)
        val userEmail :TextView = navHeader.findViewById(R.id.txt_nv_head_email)
        val userPhone : TextView = navHeader.findViewById(R.id.txt_nv_head_phone)

        userName.text = sharedPreferences.getString(LoginActivity.USER_NAME, LoginActivity.USER_NAME)
        userEmail.text = sharedPreferences.getString(LoginActivity.USER_EMAIL, LoginActivity.USER_EMAIL)
        userPhone.text = sharedPreferences.getString(LoginActivity.USER_MOBILE, LoginActivity.USER_MOBILE)

        val navigationView = binding.navView as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItems ->
            when (menuItems.itemId) {
                R.id.nav_cart_page -> {
                    val cartFragment = CartFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_full, cartFragment)
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
                R.id.nav_profile ->{
                    val userId = sharedPreferences.getInt(USER_ID, -1)
                    if(userId != -1) {
                        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                        startActivity(intent)
                    }else{
                        showDialog(
                            getString(R.string.approve),
                            getString(R.string.message_approve),
                            getString(R.string.approved)
                        )
                    }
                }
                R.id.nav_logout ->{
                    viewModel.signOut()
                    val intent = Intent(this@MainActivity, EntryActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        binding.btmAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.cart -> {
                    val cartFragment = CartFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, cartFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.homePage -> {
                    val homePageFragment = HomePageFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, homePageFragment)
                        .addToBackStack(null)
                        .commit()

                }
                R.id.order -> {
                    val orderFragment = OrderFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main, orderFragment)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.account -> {
                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.rating ->{
                    val intent = Intent(this@MainActivity, MapsActivity::class.java)
                    startActivity(intent)
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

    private fun showDialog(title:String, message:String,toastMessage:String){

        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Confirm"){_,_ ->
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)

            }
            setNegativeButton("Cancel"){_,_ ->
                makeToast(context, "Stay with Guest Account")
            }
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
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