package com.example.secondprojectbymvvm.model

import com.example.secondprojectbymvvm.model.Constants.FILTER_BY_AREA
import com.example.secondprojectbymvvm.model.Constants.FILTER_BY_CATEGORY
import com.example.secondprojectbymvvm.model.Constants.FILTER_BY_INGREDIENT
import com.example.secondprojectbymvvm.model.Constants.LIST_ALL_MEAL_CATEGORY
import com.example.secondprojectbymvvm.model.Constants.SEARCH_MEAL_BY_ID
import com.example.secondprojectbymvvm.model.Constants.SEARCH_MEAL_BY_NAME
import com.example.secondprojectbymvvm.model.data.category.CategoryResponse
import com.example.secondprojectbymvvm.model.data.meal.MealResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(LIST_ALL_MEAL_CATEGORY)
    suspend fun getCategoryInfo(): Response<CategoryResponse>

    @GET(SEARCH_MEAL_BY_NAME)
    fun searchByMealName(
        @Query("s") message: String
    ): Single<MealResponse>

    @GET(SEARCH_MEAL_BY_ID)
    fun searchByMealId(
        @Query("i") message: String
    ):Single<MealResponse>

    @GET(FILTER_BY_INGREDIENT)
    fun searchByIngredient(
        @Query("i")message:String
    ):Single<MealResponse>

    @GET(FILTER_BY_AREA)
    fun searchByArea(
        @Query("a")message:String
    ): Single<MealResponse>

    @GET(FILTER_BY_CATEGORY)
    fun searchByMealCategory(
        @Query("c")message: String
    ):Single<MealResponse>

    companion object{
        fun getInstance() = ApiClient.getRetrofit().create(ApiService::class.java)
    }
}