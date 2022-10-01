package com.example.secondprojectbymvvm.view.mealitemlist.mealdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewMealDetailsBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.view.YoutubeFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class MealDetailsAdapter(
    private var viewModel: CategoryViewModel,
    private var mealArrayList: ArrayList<Meal>,
    private var context: MealDetailsFragment
) : RecyclerView.Adapter<MealDetailsAdapter.MealDetailsHolder>() {

    private lateinit var binding: ItemViewMealDetailsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewMealDetailsBinding.inflate(layoutInflater, parent,false)
        return MealDetailsHolder(binding.root)

    }

    override fun getItemCount() = mealArrayList.size


    override fun onBindViewHolder(holder: MealDetailsHolder, position: Int) {
        holder.apply {
            val meal = mealArrayList[position]
            meal.apply {
                mealDetailsName.text = strMeal
                mealDetailsArea.text = strArea
                mealDetailsDesc.text = strInstructions
                Glide.with(context)
                    .load(strMealThumb)
                    .into(mealDetailsImg)
            }
            btnYoutube.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val youtubeFragment = YoutubeFragment()
                val bundle = Bundle()
                val url = meal.strYoutube
                bundle.putString(YOUTUBE_URL, url)
                youtubeFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_full, youtubeFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    inner class MealDetailsHolder(view:View):RecyclerView.ViewHolder(view) {
        val mealDetailsImg : ImageView = binding.imgMealDetails
        val mealDetailsName: TextView = binding.txtMealDetailsName
        val mealDetailsArea: TextView = binding.txtMealDetailsArea
        val mealDetailsDesc: TextView = binding.txtMealDetailsDesc
        val mealDetailsRating : TextView = binding.txtMealDetailsRating
        val btnYoutube:ImageView = binding.imgYoutube

    }
    companion object{
        const val YOUTUBE_URL = "url"
    }
}