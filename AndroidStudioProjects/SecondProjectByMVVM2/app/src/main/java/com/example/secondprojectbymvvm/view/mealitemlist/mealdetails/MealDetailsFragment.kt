package com.example.secondprojectbymvvm.view.mealitemlist.mealdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentMealDetailsBinding
import com.example.secondprojectbymvvm.view.mealitemlist.MealListAdapter.Companion.MEAL_ID
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class MealDetailsFragment : Fragment() {

    private lateinit var binding:FragmentMealDetailsBinding
    private lateinit var mealViewModel : CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpObserver()
    }


    private fun setUpView() {
        binding.apply {
            rvMealDetails.layoutManager = LinearLayoutManager(context)
        }
    }


    private fun setUpViewModel() {
        mealViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        mealViewModel.searchByMealId(arguments?.getString(MEAL_ID) ?: "" )
    }
    private fun setUpObserver(){
        mealViewModel.mealLiveData.observe(viewLifecycleOwner){
            binding.rvMealDetails.adapter = MealDetailsAdapter(
                mealViewModel, it.meals ,this)
        }
    }
}