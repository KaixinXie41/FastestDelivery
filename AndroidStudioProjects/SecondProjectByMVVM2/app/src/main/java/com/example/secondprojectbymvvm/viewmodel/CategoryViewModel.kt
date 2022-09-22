package com.example.secondprojectbymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secondprojectbymvvm.model.ApiClient
import com.example.secondprojectbymvvm.model.ApiService
import com.example.secondprojectbymvvm.model.data.Category
import com.example.secondprojectbymvvm.model.data.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CategoryViewModel:ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    val categoryLiveData = MutableLiveData<CategoryResponse>()

    fun getAllCategory(){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        val categoryInfo = apiService.getCategoryInfo()
        categoryInfo.enqueue(object : Callback<CategoryResponse>{
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>) {
                categoryLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
            }
        })
    }
}

