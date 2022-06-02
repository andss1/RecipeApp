package com.example.recipeapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.entities.Recipe
import com.example.recipeapp.ui.adapters.MainCategoryAdapter
import com.example.recipeapp.ui.adapters.SubCategoryAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var arrMainRecipe = ArrayList<Recipe>()
    var arrSubRecipe = ArrayList<Recipe>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Temp Data
        arrMainRecipe.add(Recipe(1, "Pizza"))
        arrMainRecipe.add(Recipe(2, "Pastel"))
        arrMainRecipe.add(Recipe(3, "Salada"))
        arrMainRecipe.add(Recipe(4, "Frango"))

        mainCategoryAdapter.setData(arrMainRecipe)

        arrSubRecipe.add(Recipe(1, "Pizza e massas"))
        arrSubRecipe.add(Recipe(1, "Pasteis e salgados"))
        arrSubRecipe.add(Recipe(1, "Saladas e verduras"))
        arrSubRecipe.add(Recipe(1, "Carnes"))

        subCategoryAdapter.setData(arrSubRecipe)

        rv_main_category.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_main_category.adapter = mainCategoryAdapter

        rv_sub_category.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_sub_category.adapter = subCategoryAdapter
    }
}