package com.example.secondprojectbymvvm.view.mealitemlist.area

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.FragmentAreaListBinding
import com.example.secondprojectbymvvm.view.homepage.search.SearchByAreaFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class AreaListFragment : Fragment() {

    private lateinit var binding: FragmentAreaListBinding
    private lateinit var mealViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAreaListBinding.inflate(inflater, container, false)
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
        mealViewModel.searchByMealArea(arguments?.getString(SearchByAreaFragment.SEARCH_TEXT) ?: "")
    }

    private fun setUpView() {
        binding.apply {
            rvAreaMealList.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpObserver() {
        mealViewModel.areaLiveData.observe(viewLifecycleOwner) {
            binding.rvAreaMealList.adapter = AreaListAdapter(
                mealViewModel, it.meals, this.requireContext())
        }
    }
}




