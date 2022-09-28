package com.example.secondprojectbymvvm.view.homepage.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentSearchByIngredientBinding
import com.example.secondprojectbymvvm.view.mealitemlist.MealListFragment
import com.example.secondprojectbymvvm.view.mealitemlist.ingredient.IngredientsFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class SearchByIngredientFragment : Fragment() {
    private lateinit var binding: FragmentSearchByIngredientBinding
    private lateinit var searchViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchByIngredientBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpObserver()
        initView()
    }

    private fun initView() {
        binding.apply {
            imgBtnSearch.setOnClickListener{
                val searchText = edtSearch.text.toString()
                val bundle = Bundle()
                bundle.putString(SEARCH_TEXT, searchText)
                searchViewModel.searchByMealIngredient(searchText)
                val ingredientsFragment = IngredientsFragment()
                ingredientsFragment.arguments = bundle
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_main,ingredientsFragment)
                    .commit()
            }
        }
    }

    private fun setUpObserver() {
        searchViewModel.mealLiveData.observe(viewLifecycleOwner){
            binding.edtSearch.text.toString()
        }
    }

    private fun setUpViewModel() {
        searchViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
    }

    companion object{
        const val SEARCH_TEXT = "searchText"
    }
}