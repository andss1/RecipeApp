package com.example.recipeapp.retrofit.interfaces

import com.example.recipeapp.entities.Category
import com.example.recipeapp.entities.Meal
import com.example.recipeapp.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IGetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>
}