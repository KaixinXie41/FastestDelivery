package com.example.secondprojectbymvvm.view.homepage.search

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentMealListBinding
import com.example.secondprojectbymvvm.databinding.FragmentSearchBinding
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.model.data.meal.MealResponse
import com.example.secondprojectbymvvm.view.mealitemlist.MealListFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel
import com.google.android.gms.dynamic.SupportFragmentWrapper

class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private lateinit var searchViewModel:CategoryViewModel
    private lateinit var mealLivedata:MutableLiveData<MealResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
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
                searchViewModel.searchByMealName(searchText)
                val mealListFragment = MealListFragment()
                mealListFragment.arguments = bundle
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_main,mealListFragment)
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