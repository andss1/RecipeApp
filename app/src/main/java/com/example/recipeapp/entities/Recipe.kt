package com.example.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "Recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "recipeName")
    var recipeName: String
) : Serializable
