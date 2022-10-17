package com.example.secondprojectbymvvm.view.mealitemlist.mealdetails

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewMealDetailsBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.entities.Favorite
import com.example.secondprojectbymvvm.model.local.dao.FavoriteDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.USER_ID
import com.example.secondprojectbymvvm.view.homepage.other.YoutubeFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class MealDetailsAdapter(
    private var viewModel: CategoryViewModel,
    private var mealArrayList: ArrayList<Meal>,
    private var context: Context
) : RecyclerView.Adapter<MealDetailsAdapter.MealDetailsHolder>() {

    private lateinit var binding: ItemViewMealDetailsBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var appDatabase: AppDatabase
    private lateinit var favoriteDao: FavoriteDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewMealDetailsBinding.inflate(layoutInflater, parent,false)
        appDatabase = AppDatabase.getInstance(context)
        favoriteDao = appDatabase.getFavoriteDao()
        sharedPreferences = context.getSharedPreferences(LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return MealDetailsHolder(binding.root)

    }

    override fun getItemCount() = mealArrayList.size


    override fun onBindViewHolder(holder: MealDetailsHolder, position: Int) {
        holder.apply {
            val meal = mealArrayList[position]
            val mealFromFavorite = favoriteDao.getFavoriteByMealId(meal.idMeal.toLong())
            if(mealFromFavorite !=null){
                btnFavorite.isChecked = mealFromFavorite.mealId.toString() == meal.idMeal
            }
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
            btnFavorite.setOnClickListener {
                val favoriteMealName = meal.strMeal
                val favoriteMealId = meal.idMeal
                val userId = sharedPreferences.getInt(USER_ID, 0)
                val favoriteMealPicture = meal.strMealThumb
                val favorite = Favorite(

                    favoriteMealId.toLong(),
                    favoriteMealName,
                    userId,
                    favoriteMealPicture
                )
                if(btnFavorite.isChecked) {
                    favoriteDao.addFavorite(favorite)
                }else{
                    favoriteDao.deleteFavorite(favorite)
                }
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
        val btnFavorite : ToggleButton = binding.btnFavorite

    }
    companion object{
        const val YOUTUBE_URL = "url"
    }
}