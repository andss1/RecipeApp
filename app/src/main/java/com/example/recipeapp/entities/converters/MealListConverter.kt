package com.example.recipeapp.entities.converters

import androidx.room.TypeConverter
import com.example.recipeapp.entities.MealItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {
    @TypeConverter
    fun listToJson(meal: ArrayList<MealItems>): String? {
        if (meal == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object : TypeToken<MealItems>() {}.type

            return gson.toJson(meal, type)
        }
    }

    @TypeConverter
    fun toCategoryList(mealString: String): ArrayList<MealItems>? {
        if (mealString == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object : TypeToken<MealItems>() {}.type

            return gson.fromJson(mealString, type)
        }
    }

}