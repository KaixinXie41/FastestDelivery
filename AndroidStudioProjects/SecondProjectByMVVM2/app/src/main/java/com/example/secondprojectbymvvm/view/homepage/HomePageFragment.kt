package com.example.secondprojectbymvvm.view.homepage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.FragmentHomePageBinding
import com.example.secondprojectbymvvm.model.data.Offer
import com.example.secondprojectbymvvm.view.mealitemlist.AreaItemFragment
import com.example.secondprojectbymvvm.view.mealitemlist.CategoryItemFragment
import com.example.secondprojectbymvvm.view.mealitemlist.IngredientsFragment
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var offerAdapter: HomePageOfferAdapter
    private val offer = ArrayList<Offer>()
    private lateinit var categoryViewModel : CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpOberser()
        initView()

    }


    private fun setUpOberser() {
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner){
            binding.rvCategory.adapter = HomePageCategoryAdapter(
                categoryViewModel,  it.categories ,this)
        }
    }

    private fun setUpViewModel() {
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.getAllCategory()
    }

    private fun setUpView() {
        binding.apply {
            rvCategory.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun initView() {
        offer.apply {
            add(Offer(
                "FirePot Weekend!",
                "Are you ready for the weekend party?! Let's get 25% off of party meal!",
            "Sept.22 to Sept.24",
            R.drawable.partymeal))
            add(Offer(
                "FirePot Weekend!",
                "Are you ready for the weekend party?! Let's get 25% off of party meal!",
                "Sept.22 to Sept.24",
                R.drawable.partymeal))
            add(Offer(
                "FirePot Weekend!",
                "Are you ready for the weekend party?! Let's get 25% off of party meal!",
                "Sept.22 to Sept.24",
                R.drawable.partymeal))
            add(Offer(
                "FirePot Weekend!",
                "Are you ready for the weekend party?! Let's get 25% off of party meal!",
                "Sept.22 to Sept.24",
                R.drawable.partymeal))
        }
        offerAdapter = HomePageOfferAdapter(offer)
        binding.rvOffer.layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        binding.rvOffer.adapter = offerAdapter

        binding.btnOption.shrink()
        var isCollasped = false
        binding.apply {
            btnOption.setOnClickListener{
                if(!isCollasped){
                    btnOption.show()
                    btnOptionArea.show()
                    btnOptionIngredient.show()
                    isCollasped = true
                }else{
                    btnOptionArea.hide()
                    btnOptionIngredient.hide()
                    isCollasped = false
                }
            }
            btnOptionArea.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val areaItemFragment = AreaItemFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_main, areaItemFragment)
                    .addToBackStack(null)
                    .commit()
            }
            btnOptionIngredient.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val ingredientsFragment = IngredientsFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_main, ingredientsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}