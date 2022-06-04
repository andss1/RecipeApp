package com.example.recipeapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.entities.MealItems
import kotlinx.android.synthetic.main.item_rv_sub_category.view.*

class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var arrSubCategory = ArrayList<MealItems>()
    var ctx: Context? = null

    fun setData(arrData: List<MealItems>) {
        arrSubCategory = arrData as ArrayList<MealItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_sub_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arrSubCategory[position].strMeal
        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb)
            .into(holder.itemView.img_dish)
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }
}