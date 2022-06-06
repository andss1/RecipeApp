package com.example.recipeapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.entities.CategoryItems
import com.example.recipeapp.entities.MealItems
import com.example.recipeapp.ui.adapters.MainCategoryAdapter
import com.example.recipeapp.ui.adapters.SubCategoryAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealItems>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    private val onMainItemClicked = object : MainCategoryAdapter.OnItemClickListener {
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onSubItemClicked = object : SubCategoryAdapter.OnItemClickListener {
        override fun onClicked(mealId: String) {
            var intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("id", mealId)
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDb()

        mainCategoryAdapter.setClickListener(onMainItemClicked)

        subCategoryAdapter.setClickListener(onSubItemClicked)


        rv_sub_category.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_sub_category.adapter = subCategoryAdapter
    }


    private fun getDataFromDb() {
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()

                getMealDataFromDb(arrMainCategory[0].strcategory)

                mainCategoryAdapter.setData(arrMainCategory)
                rv_main_category.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_main_category.adapter = mainCategoryAdapter
            }

        }
    }

    private fun getMealDataFromDb(categoryName: String) {
        tvCategory.text = categoryName + " Category"

        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao()
                    .getSpecificMeal(categoryName)
                arrSubCategory = cat as ArrayList<MealItems>

                subCategoryAdapter.setData(arrSubCategory)
                rv_sub_category.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_sub_category.adapter = subCategoryAdapter
            }

        }
    }
}