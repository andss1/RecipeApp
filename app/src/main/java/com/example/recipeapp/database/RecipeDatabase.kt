package com.example.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.dao.RecipeDao
import com.example.recipeapp.entities.Category
import com.example.recipeapp.entities.CategoryItems
import com.example.recipeapp.entities.Recipe
import com.example.recipeapp.entities.converters.CategoryListConverter

@Database(
    entities = [Recipe::class, CategoryItems::class, Category::class, CategoryListConverter::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    companion object {
        var recipiesDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipiesDatabase != null) {
                recipiesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipiesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}
