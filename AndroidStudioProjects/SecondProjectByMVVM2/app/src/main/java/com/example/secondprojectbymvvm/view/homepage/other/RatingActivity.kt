package com.example.secondprojectbymvvm.view.homepage.other

import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ActivityRatingBinding
import com.google.android.material.snackbar.Snackbar

class RatingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            ratingBar.setOnRatingBarChangeListener { _, _, _ ->
                when (binding.ratingBar.rating.toInt()) {
                    0 -> {
                        imgEmoji.setImageResource(R.drawable.empty)
                    }
                    1 -> {
                        imgEmoji.setImageResource(R.drawable.angry)
                    }
                    2 -> {
                        imgEmoji.setImageResource(R.drawable.unhappy)
                    }
                    3 -> {
                        imgEmoji.setImageResource(R.drawable.normal)
                    }
                    4 -> {
                        imgEmoji.setImageResource(R.drawable.smile)
                    }
                    5 -> {
                        imgEmoji.setImageResource(R.drawable.loveit)
                    }
                }
            }
            btnSubmit.setOnClickListener {
                val ratingAmount = binding.ratingBar.rating.toString()
                val snackbar = Snackbar.make(it, "Thank you for rating this app! Your rating is $ratingAmount", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.GRAY)
                snackbar.show()
            }
        }
    }
}

