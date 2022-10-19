package com.example.secondprojectbymvvm.view.homepage.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentSearchBinding
import com.example.secondprojectbymvvm.model.ApiService
import com.example.secondprojectbymvvm.model.data.category.CategoryRepository
import com.example.secondprojectbymvvm.model.data.meal.MealResponse
import com.example.secondprojectbymvvm.view.mealitemlist.meallist.MealListFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModelProvider

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
        val repository = CategoryRepository(ApiService.getInstance())
        val factory = CategoryViewModelProvider(repository)
        searchViewModel = ViewModelProvider(this,factory)[CategoryViewModel::class.java]
    }

    companion object{
        const val SEARCH_TEXT = "searchText"
    }
}