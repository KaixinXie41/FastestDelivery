package com.example.secondprojectbymvvm.view.mealitemlist.ingredient

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
import com.example.secondprojectbymvvm.databinding.ItemViewMealBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.entities.Favorite
import com.example.secondprojectbymvvm.model.local.dao.FavoriteDao
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.mealitemlist.mealdetails.MealDetailsFragment
import com.example.secondprojectbymvvm.view.mealitemlist.MealListAdapter.Companion.MEAL_ID
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class IngredientAdapter(
    private val viewModel: CategoryViewModel,
    private val mealList: List<Meal>,
    private val context: Context
)
    :RecyclerView.Adapter<IngredientAdapter.MealViewHolder>(){
    private lateinit var binding : ItemViewMealBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var appDatabase: AppDatabase
    private lateinit var favoriteDao: FavoriteDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewMealBinding.inflate(layoutInflater, parent,false)
        appDatabase = AppDatabase.getInstance(context)
        favoriteDao = appDatabase.getFavoriteDao()
        sharedPreferences = context.getSharedPreferences(LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return MealViewHolder(binding.root)
    }

    override fun getItemCount() = mealList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.apply {
            val meal = mealList[position]
            val mealFromFavorite = favoriteDao.getFavoriteByMealId(meal.idMeal.toLong())
            if(mealFromFavorite !=null){
                favoriteButton.isChecked = mealFromFavorite.mealId.toString() == meal.idMeal
            }
            mealName.text = meal.strMeal
            mealArea.text = meal.strArea
            mealCategory.text = meal.strCategory
            Glide.with(context)
                .load(meal.strMealThumb)
                .into(imgMeal)
            itemView.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val mealDetailsFragment = MealDetailsFragment()
                val bundle = Bundle()
                val mealId = meal.idMeal
                bundle.putString(MEAL_ID, mealId)
                mealDetailsFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_full, mealDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
            favoriteButton.setOnClickListener {
                val favoriteMealName = meal.strMeal
                val favoriteMealId = meal.idMeal
                val userId = sharedPreferences.getInt(LoginActivity.USER_ID, 0)
                val favoriteMealPicture = meal.strMealThumb
                val favorite = Favorite(
                    favoriteMealId.toLong(),
                    favoriteMealName,
                    userId,
                    favoriteMealPicture
                )
                if (favoriteButton.isChecked) {
                    favoriteDao.addFavorite(favorite)
                    favoriteButton.isChecked = true
                } else {
                    favoriteDao.deleteFavorite(favorite)
                    favoriteButton.isChecked = false
                }
            }
        }
    }

    inner class MealViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val imgMeal: ImageView = binding.imgMeal
        val mealName: TextView = binding.txtMealName
        val mealArea: TextView = binding.txtMealArea
        val mealCategory: TextView = binding.txtMealCategory
        val mealRating: TextView = binding.txtMealRating
        val favoriteButton :ToggleButton = binding.btnFavorite

    }

    companion object{
        const val MEAL_INGREDIENT = "mealIngredient"
    }
}