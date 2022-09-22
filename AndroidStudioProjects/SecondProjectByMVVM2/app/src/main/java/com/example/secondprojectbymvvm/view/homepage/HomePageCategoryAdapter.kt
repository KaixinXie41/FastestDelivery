package com.example.secondprojectbymvvm.view.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewCategoryBinding
import com.example.secondprojectbymvvm.model.data.Category
import com.example.secondprojectbymvvm.view.mealitemlist.CategoryItemFragment


class HomePageCategoryAdapter(
    val viewModel: ViewModel,
    val categoryList: List<Category>,
    val context: HomePageFragment,
)
    :RecyclerView.Adapter<HomePageCategoryAdapter.CategoryHolder>(){
    private lateinit var binding:ItemViewCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryHolder(binding.root)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.apply {
            val category = categoryList[position]
            categoryName.text = category.strCategory
            categoryDesc.text = category.strCategoryDescription
            Glide.with(context)
                .load(category.strCategoryThumb)
                .into(imgCategory)
            itemView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(p0: View?) {
                    val activity = p0!!.context as AppCompatActivity
                    val categoryItemFragment = CategoryItemFragment()
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout_main,categoryItemFragment)
                        .addToBackStack(null)
                        .commit()
                }
            })
        }
    }
    inner class CategoryHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val imgCategory: ImageView = binding.imgCategory
        val categoryName: TextView = binding.txtCategoryName
        val categoryDesc: TextView = binding.txtCategoryDesc
    }

}