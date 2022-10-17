package com.example.secondprojectbymvvm.view.homepage.other

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivitySplashScreenBinding
import com.example.secondprojectbymvvm.model.Constants.SPLASH_TIME1
import com.example.secondprojectbymvvm.model.Constants.SPLASH_TIME2
import com.example.secondprojectbymvvm.view.authentication.EntryActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var binding:ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        binding.splashScreenImage.startAnimation(slideAnimation)

        val bikeSlide = AnimationUtils.loadAnimation(this, R.anim.bike_slide)
        binding.imgDeliveryCart.startAnimation(bikeSlide)

        val zoomInTitle = AnimationUtils.loadAnimation(this, R.anim.title_zoom_in)
        binding.txtSplashTitle.startAnimation(zoomInTitle)

        Handler().postDelayed({
            val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            binding.splashScreenImage.startAnimation(rotateAnimation)
        },SPLASH_TIME1)

        Handler().postDelayed({
            val intent = Intent(this, EntryActivity::class.java)
            startActivity(intent)
            finish()
        },SPLASH_TIME2)
    }
}