package com.example.recipeapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.entities.Recipe

@Dao
interface RecipeDao {
    @get:Query("SELECT * FROM recipes ORDER BY id DESC")
    val allRecipes: List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipie: Recipe)
}