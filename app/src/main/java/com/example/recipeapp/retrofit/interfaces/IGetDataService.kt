package com.example.recipeapp.retrofit.interfaces

import com.example.recipeapp.entities.Category
import retrofit2.Call
import retrofit2.http.GET

interface IGetDataService {
    @GET("/categories.php")
    fun getCategoryList(): Call<List<Category>>
}