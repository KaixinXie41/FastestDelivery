package com.example.secondprojectbymvvm.model

import com.example.secondprojectbymvvm.model.Constants.FILTER_BY_AREA
import com.example.secondprojectbymvvm.model.Constants.FILTER_BY_CATEGORY
import com.example.secondprojectbymvvm.model.Constants.FILTER_BY_INGREDIENT
import com.example.secondprojectbymvvm.model.Constants.LIST_ALL_MEAL_CATEGORY
import com.example.secondprojectbymvvm.model.Constants.SEARCH_MEAL_BY_ID
import com.example.secondprojectbymvvm.model.Constants.SEARCH_MEAL_BY_NAME
import com.example.secondprojectbymvvm.model.data.category.Category
import com.example.secondprojectbymvvm.model.data.category.CategoryResponse
import com.example.secondprojectbymvvm.model.data.meal.Meal
import com.example.secondprojectbymvvm.model.data.meal.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(LIST_ALL_MEAL_CATEGORY)
    fun getCategoryInfo(): Call<CategoryResponse>

    @GET(SEARCH_MEAL_BY_NAME)
    fun searchByMealName(
        @Query("s") message: String
    ): Call<MealResponse>

    @GET(SEARCH_MEAL_BY_ID)
    fun searchByMealId(
        @Query("i") message: String
    ):Call<MealResponse>

    @GET(FILTER_BY_INGREDIENT)
    fun searchByIngredient(
        @Query("i")message:String
    ):Call<MealResponse>

    @GET(FILTER_BY_AREA)
    fun searchByArea(
        @Query("a")message:String
    ):Call<MealResponse>

    @GET(FILTER_BY_CATEGORY)
    fun searchByMealCategory(
        @Query("c")message: String
    ):Call<MealResponse>
}