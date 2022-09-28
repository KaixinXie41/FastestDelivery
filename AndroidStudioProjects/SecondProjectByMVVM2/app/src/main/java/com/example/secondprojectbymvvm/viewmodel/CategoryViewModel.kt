package com.example.secondprojectbymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secondprojectbymvvm.model.ApiClient
import com.example.secondprojectbymvvm.model.ApiService
import com.example.secondprojectbymvvm.model.data.category.CategoryResponse
import com.example.secondprojectbymvvm.model.data.meal.MealResponse
import retrofit2.*

class CategoryViewModel:ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    val categoryLiveData = MutableLiveData<CategoryResponse>()
    val mealLiveData = MutableLiveData<MealResponse>()


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

    fun searchByMealName(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        val mealInfo = apiService.searchByMealName(message)
        mealInfo.enqueue(object : Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                mealLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
            }
        })
    }
    fun searchByMealArea(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        val mealInfo = apiService.searchByArea(message)
        mealInfo.enqueue(object : Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                mealLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
            }
        })
    }
    fun searchByMealIngredient(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        val mealInfo = apiService.searchByIngredient(message)
        mealInfo.enqueue(object : Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                mealLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
            }
        })
    }
    fun searchByMealId(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        val mealInfo = apiService.searchByMealId(message)
        mealInfo.enqueue(object : Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                mealLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
            }
        })
    }

    fun searchByMealCategory(message: String) {
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        val mealInfo = apiService.searchByMealCategory(message)
        mealInfo.enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                mealLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
            }
        })
    }

}

