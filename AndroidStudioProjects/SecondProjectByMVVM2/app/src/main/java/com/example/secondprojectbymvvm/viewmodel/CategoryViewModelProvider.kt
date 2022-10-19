package com.example.secondprojectbymvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.secondprojectbymvvm.model.data.category.CategoryRepository

class CategoryViewModelProvider(private val repository: CategoryRepository)
    :ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }
}