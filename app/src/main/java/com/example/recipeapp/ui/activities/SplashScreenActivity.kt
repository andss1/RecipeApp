package com.example.recipeapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.R
import com.example.recipeapp.entities.Category
import com.example.recipeapp.retrofit.RetrofitClientInstance
import com.example.recipeapp.retrofit.interfaces.IGetDataService
import kotlinx.android.synthetic.main.activity_splashscreen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        btnGetStarted.setOnClickListener {
            var intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getCategories() {
        val service =
            RetrofitClientInstance.retrofitInstance.create(IGetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                insertDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashScreenActivity, "Algo deu errado", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun insertDataIntoRoomDb(categories: List<Category>?) {

    }
}