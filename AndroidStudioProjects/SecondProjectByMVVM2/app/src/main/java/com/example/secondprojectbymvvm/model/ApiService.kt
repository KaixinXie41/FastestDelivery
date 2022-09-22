package com.example.secondprojectbymvvm.model

import com.example.secondprojectbymvvm.model.Constants.LIST_ALL_MEAL_CATEGORY
import com.example.secondprojectbymvvm.model.data.Category
import com.example.secondprojectbymvvm.model.data.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(LIST_ALL_MEAL_CATEGORY)
    fun getCategoryInfo(): Call<CategoryResponse>
}