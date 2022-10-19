package com.example.secondprojectbymvvm.model.data.category

import com.example.secondprojectbymvvm.model.ApiService

class CategoryRepository(private val apiService: ApiService) {
    suspend fun getAllCategory() = apiService.getCategoryInfo()
}