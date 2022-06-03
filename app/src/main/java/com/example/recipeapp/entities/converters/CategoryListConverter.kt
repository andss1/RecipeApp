package com.example.recipeapp.entities.converters

import androidx.room.TypeConverter
import com.example.recipeapp.entities.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {
    @TypeConverter
    fun fromCategoryList(categories: List<Category>): String? {
        if (categories == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object : TypeToken<Category>() {}.type

            return gson.toJson(categories, type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString: String): List<Category>? {
        if (categoryString == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object : TypeToken<Category>() {}.type

            return gson.fromJson(categoryString, type)
        }
    }
}