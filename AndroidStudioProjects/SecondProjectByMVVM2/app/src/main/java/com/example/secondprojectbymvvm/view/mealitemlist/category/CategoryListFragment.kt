package com.example.secondprojectbymvvm.view.mealitemlist.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentCategoryListFragementBinding
import com.example.secondprojectbymvvm.view.homepage.HomePageCategoryAdapter.Companion.MEAL_CATEGORY
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel


class CategoryListFragment : Fragment() {


    private lateinit var binding: FragmentCategoryListFragementBinding
    private lateinit var mealViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryListFragementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpObserver()
    }


    private fun setUpViewModel() {
        mealViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        mealViewModel.searchByMealCategory(arguments?.getString(MEAL_CATEGORY) ?: "")
    }

    private fun setUpView() {
        binding.apply {
            rvCategoryMealList.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpObserver() {
        mealViewModel.mealLiveData.observe(viewLifecycleOwner) {
            binding.rvCategoryMealList.adapter = CategoryAdapter(
                mealViewModel, it.meals, this
            )
        }
    }
}



