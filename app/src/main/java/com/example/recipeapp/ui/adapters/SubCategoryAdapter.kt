package com.example.recipeapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.entities.Recipe
import kotlinx.android.synthetic.main.item_rv_main_category.view.*

class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var arrSubRecipes = ArrayList<Recipe>()

    fun setData(arrData: List<Recipe>){
        arrSubRecipes = arrData as ArrayList<Recipe>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_sub_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arrSubRecipes[position].recipeName
    }

    override fun getItemCount(): Int {
        return arrSubRecipes.size
    }
}