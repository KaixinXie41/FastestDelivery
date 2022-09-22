package com.example.secondprojectbymvvm.model

import com.example.secondprojectbymvvm.model.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private lateinit var myRetrofit: Retrofit
    fun getRetrofit():Retrofit{
        if(!this ::myRetrofit.isInitialized){
            myRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return myRetrofit
    }
}