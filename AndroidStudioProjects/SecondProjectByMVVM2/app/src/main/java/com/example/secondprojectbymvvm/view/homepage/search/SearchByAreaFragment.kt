package com.example.secondprojectbymvvm.view.homepage.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentSearchByAreaBinding
import com.example.secondprojectbymvvm.model.ApiService
import com.example.secondprojectbymvvm.model.data.category.CategoryRepository
import com.example.secondprojectbymvvm.view.mealitemlist.area.AreaListFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModelProvider

class SearchByAreaFragment : Fragment() {

    private lateinit var binding:FragmentSearchByAreaBinding
    private lateinit var searchViewModel:CategoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchByAreaBinding.inflate(inflater, container, false)
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
                searchViewModel.searchByMealArea(searchText)
                val areaListFragment = AreaListFragment()
                areaListFragment.arguments = bundle
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_main,areaListFragment)
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
