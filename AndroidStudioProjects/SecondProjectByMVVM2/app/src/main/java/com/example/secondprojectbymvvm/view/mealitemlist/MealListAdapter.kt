package com.example.secondprojectbymvvm.view.mealitemlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewMealBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.view.mealitemlist.mealdetails.MealDetailsFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class MealListAdapter(
    private val viewModel: CategoryViewModel,
    private val mealList: List<Meal>,
    private val context: MealListFragment
    )
    : RecyclerView.Adapter<MealListAdapter.MealViewHolder>(){

    private lateinit var binding: ItemViewMealBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewMealBinding.inflate(layoutInflater, parent,false)
        return MealViewHolder(binding.root)
    }

    override fun getItemCount() = mealList.size


    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.apply {
            val meal = mealList[position]
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
        }
    }

    inner class MealViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val imgMeal : ImageView = binding.imgMeal
        val mealName: TextView = binding.txtMealName
        val mealArea: TextView = binding.txtMealArea
        val mealCategory: TextView = binding.txtMealCategory
        val mealRating : TextView = binding.txtMealRating

    }
    companion object{
        const val MEAL_ID ="mealId"
    }
}