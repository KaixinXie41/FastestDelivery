package com.example.secondprojectbymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.secondprojectbymvvm.model.ApiClient
import com.example.secondprojectbymvvm.model.ApiService
import com.example.secondprojectbymvvm.model.data.category.CategoryResponse
import com.example.secondprojectbymvvm.model.data.meal.MealResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.*

class CategoryViewModel:ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    val categoryLiveData = MutableLiveData<CategoryResponse>()
    val mealLiveData = MutableLiveData<MealResponse>()
    val areaLiveData = MutableLiveData<MealResponse>()
    val booleanLiveData = MutableLiveData<Boolean>(false)
    lateinit var disposable : Disposable
    val compositeDisposable = CompositeDisposable()


    fun getAllCategory(){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        disposable = apiService.getCategoryInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                booleanLiveData.value = true
            }
            .doOnTerminate {
                booleanLiveData.value = false
            }
            .subscribe( {response ->
                categoryLiveData.postValue(response)

            },{t->
                t.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    fun searchByMealName(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        disposable = apiService.searchByMealName(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                booleanLiveData.value = true
            }
            .doOnTerminate {
                booleanLiveData.value = false
            }
            .subscribe( {response ->
                mealLiveData.postValue(response)

            },{t->
                t.printStackTrace()
            })
        compositeDisposable.add(disposable)

    }
    fun searchByMealArea(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        disposable = apiService.searchByArea(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                booleanLiveData.value = true
            }
            .doOnTerminate {
                booleanLiveData.value = false
            }
            .subscribe( {response ->
                areaLiveData.postValue(response)

            },{t->
                t.printStackTrace()
            })
        compositeDisposable.add(disposable)

    }
    fun searchByMealIngredient(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        disposable = apiService.searchByIngredient(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                booleanLiveData.value = true
            }
            .doOnTerminate {
                booleanLiveData.value = false
            }
            .subscribe( {response ->
                mealLiveData.postValue(response)

            },{t->
                t.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }
    fun searchByMealId(message:String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        disposable = apiService.searchByMealId(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                booleanLiveData.value = true
            }
            .doOnTerminate {
                booleanLiveData.value = false
            }
            .subscribe( {response ->
                mealLiveData.postValue(response)

            },{t->
                t.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    fun searchByMealCategory(message: String) {
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        disposable = apiService.searchByMealCategory(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                booleanLiveData.value = true
            }
            .doOnTerminate {
                booleanLiveData.value = false
            }
            .subscribe( {response ->
                mealLiveData.postValue(response)

            },{t->
                t.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

}

