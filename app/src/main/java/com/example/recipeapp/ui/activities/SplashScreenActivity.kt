package com.example.recipeapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.recipeapp.R
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.entities.Category
import com.example.recipeapp.entities.Meal
import com.example.recipeapp.entities.MealItems
import com.example.recipeapp.retrofit.RetrofitClientInstance
import com.example.recipeapp.retrofit.interfaces.IGetDataService
import kotlinx.android.synthetic.main.activity_splashscreen.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : BaseActivity(), EasyPermissions.RationaleCallbacks,
    EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERMISSION = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        readStorageTask()

        btnGetStarted.setOnClickListener {
            var intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    fun getCategories() {
        val service =
            RetrofitClientInstance.retrofitInstance!!.create(IGetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : Callback<Category> {
            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                for (arr in response.body()!!.categorieitems!!) {
                    getMeals(arr.strcategory)
                }
                insertDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@SplashScreenActivity, "Algo deu errado", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun insertDataIntoRoomDb(categories: Category?) {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashScreenActivity).recipeDao().clearDb()

                for (arr in categories!!.categorieitems!!) {
                    RecipeDatabase.getDatabase(this@SplashScreenActivity)
                        .recipeDao().insertCategory(arr)
                }
            }
        }
    }

    fun clearDb() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashScreenActivity).recipeDao().clearDb()
            }
        }
    }

    fun getMeals(categoryName: String) {
        val service =
            RetrofitClientInstance.retrofitInstance!!.create(IGetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object : Callback<Meal> {
            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {
                insertMealDataIntoRoomDb(categoryName, response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashScreenActivity, "Algo deu errado", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun insertMealDataIntoRoomDb(categoryName: String, meal: Meal?) {
        launch {
            this.let {
                for (arr in meal!!.mealsItems!!) {
                    var mealItemModel = MealItems(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )

                    RecipeDatabase.getDatabase(this@SplashScreenActivity)
                        .recipeDao().insertMeal(mealItemModel)
                }

                btnGetStarted.visibility = View.VISIBLE
            }
        }
    }


    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun readStorageTask() {
        if (hasReadStoragePermission()) {
            clearDb()
            getCategories()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Este aplicativo precisa acessar seus arquivos",
                READ_STORAGE_PERMISSION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }

    override fun onRationaleDenied(requestCode: Int) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}