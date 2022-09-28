package com.example.secondprojectbymvvm.view.mealitemlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentMealListBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.view.homepage.search.SearchFragment
import com.example.secondprojectbymvvm.view.homepage.search.SearchFragment.Companion.SEARCH_TEXT
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class MealListFragment : Fragment() {

    private lateinit var binding:FragmentMealListBinding
    private lateinit var mealList:List<Meal>
    private lateinit var mealViewModel : CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealListBinding.inflate(inflater, container,false)
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
        mealViewModel.searchByMealName(arguments?.getString(SEARCH_TEXT) ?: "" )
    }

    private fun setUpView(){
        binding.apply {
            rvMealItemList.layoutManager = LinearLayoutManager(context)
        }
    }
    private fun setUpObserver(){
        mealViewModel.mealLiveData.observe(viewLifecycleOwner){
            binding.rvMealItemList.adapter = MealListAdapter(
                mealViewModel, it.meals ,this)
        }
    }


}