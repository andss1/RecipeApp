package com.example.recipeapp.entities.converters

import androidx.room.TypeConverter
import com.example.recipeapp.entities.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {
    @TypeConverter
    fun listToJson(category: ArrayList<CategoryItems>): String? {
        if (category == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>() {}.type

            return gson.toJson(category, type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString: String): ArrayList<CategoryItems>? {
        if (categoryString == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>() {}.type

            return gson.fromJson(categoryString, type)
        }
    }

}