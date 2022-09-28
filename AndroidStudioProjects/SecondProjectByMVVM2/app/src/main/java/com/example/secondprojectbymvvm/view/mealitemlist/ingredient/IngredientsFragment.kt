package com.example.secondprojectbymvvm.view.mealitemlist.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentIngredientsBinding
import com.example.secondprojectbymvvm.view.homepage.search.SearchFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private lateinit var mealViewModel : CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
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
        mealViewModel.searchByMealIngredient(arguments?.getString(SearchFragment.SEARCH_TEXT) ?: "" )
    }

    private fun setUpView(){
        binding.apply {
            rvIngredientMealList.layoutManager = LinearLayoutManager(context)
        }
    }
    private fun setUpObserver(){
        mealViewModel.mealLiveData.observe(viewLifecycleOwner){
            binding.rvIngredientMealList.adapter = IngredientAdapter(
                mealViewModel, it.meals ,this)
        }
    }
}