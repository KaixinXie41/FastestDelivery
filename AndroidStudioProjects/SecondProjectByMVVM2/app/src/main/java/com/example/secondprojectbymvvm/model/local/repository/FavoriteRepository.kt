package com.example.secondprojectbymvvm.model.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.FavoriteDao
import com.example.secondprojectbymvvm.model.local.entities.Favorite

class FavoriteRepository(application: Application) {
    private val favoriteDao: FavoriteDao?
    val allFavorite: LiveData<List<Favorite>>

    init {
        val database = application.let {
            AppDatabase.getInstance(it)
        }
        favoriteDao = database.getFavoriteDao()
        allFavorite = favoriteDao.getAllFavorite()
    }

    fun insert(favorite: Favorite) {
        favoriteDao?.addFavorite(favorite)
    }

    fun delete(favorite: Favorite) {
        favoriteDao?.deleteFavorite(favorite)
    }

    fun update(favorite: Favorite){
        favoriteDao?.updateFavorite(favorite)
    }

    fun getFavoriteByUserId(userId:Int){
        favoriteDao?.getFavoriteByUserId(userId)
    }
}