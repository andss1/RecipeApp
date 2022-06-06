package com.example.recipeapp.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.entities.MealResponse
import com.example.recipeapp.retrofit.RetrofitClientInstance
import com.example.recipeapp.retrofit.interfaces.IGetDataService
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_splashscreen.*
import kotlinx.android.synthetic.main.item_rv_sub_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : BaseActivity() {

    var ytLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var id = intent.getStringExtra("id")
        getSpecificItem(id!!)

        img_toolbar_btn.setOnClickListener {
            finish()
        }

        btnYouTube.setOnClickListener {
            val uri: Uri =
                Uri.parse(ytLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    fun getSpecificItem(idMeal: String) {
        val service =
            RetrofitClientInstance.retrofitInstance!!.create(IGetDataService::class.java)
        val call = service.getSpecificItem(idMeal)
        call.enqueue(object : Callback<MealResponse> {
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                if (response.body()!!.mealsEntity.isNullOrEmpty()) {
                    Toast.makeText(
                        this@DetailActivity,
                        "Os detalhes da receita não foram encontrados!",
                        Toast.LENGTH_SHORT
                    ).show()
                    scrollview.visibility = View.GONE
                } else {
                    Glide.with(this@DetailActivity)
                        .load(response.body()!!.mealsEntity[0].strmealthumb)
                        .into(img_item)

                    tvCategory.text = response.body()!!.mealsEntity[0].strmeal
                    var ingredient =
                        "${response.body()!!.mealsEntity[0].stringredient1}        ${response.body()!!.mealsEntity[0].strmeasure1}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient2}        ${response.body()!!.mealsEntity[0].strmeasure2}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient3}        ${response.body()!!.mealsEntity[0].strmeasure3}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient4}        ${response.body()!!.mealsEntity[0].strmeasure4}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient5}        ${response.body()!!.mealsEntity[0].strmeasure5}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient6}        ${response.body()!!.mealsEntity[0].strmeasure6}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient7}        ${response.body()!!.mealsEntity[0].strmeasure7}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient8}        ${response.body()!!.mealsEntity[0].strmeasure8}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient9}        ${response.body()!!.mealsEntity[0].strmeasure9}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient10}        ${response.body()!!.mealsEntity[0].strmeasure10}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient11}        ${response.body()!!.mealsEntity[0].strmeasure11}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient12}        ${response.body()!!.mealsEntity[0].strmeasure12}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient13}        ${response.body()!!.mealsEntity[0].strmeasure13}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient14}        ${response.body()!!.mealsEntity[0].strmeasure14}\n" +
                                "${response.body()!!.mealsEntity[0].stringredient15}        ${response.body()!!.mealsEntity[0].strmeasure15}\n"

                    tv_ingred.text = ingredient
                    tv_instruc.text = response.body()!!.mealsEntity[0].strinstructions

                    if (response.body()!!.mealsEntity[0].stryoutube != null) {
                        ytLink = response.body()!!.mealsEntity[0].stryoutube
                    } else {
                        btnYouTube.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@DetailActivity, "Algo deu errado", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}