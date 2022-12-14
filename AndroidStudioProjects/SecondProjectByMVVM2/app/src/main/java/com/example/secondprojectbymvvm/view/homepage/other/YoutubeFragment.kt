package com.example.secondprojectbymvvm.view.homepage.other

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.databinding.FragmentYoutubeBinding
import com.example.secondprojectbymvvm.model.ApiService
import com.example.secondprojectbymvvm.model.data.category.CategoryRepository
import com.example.secondprojectbymvvm.view.mealitemlist.mealdetails.MealDetailsAdapter.Companion.YOUTUBE_URL
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModel
import com.example.secondprojectbymvvm.viewmodel.CategoryViewModelProvider

class YoutubeFragment : Fragment() {

    private lateinit var binding : FragmentYoutubeBinding
    private lateinit var mealViewModel : CategoryViewModel
    private lateinit var websitePage:WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYoutubeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpWebsite()


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebsite() {
        websitePage = binding.webPage
        websitePage.apply {
            webViewClient = WebViewClient()
            websitePage.loadUrl(arguments?.getString(YOUTUBE_URL)?: "")
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
        }
    }

    private fun setUpViewModel() {
        val repository = CategoryRepository(ApiService.getInstance())
        val factory = CategoryViewModelProvider(repository)
        mealViewModel = ViewModelProvider(this,factory)[CategoryViewModel::class.java]
    }
}