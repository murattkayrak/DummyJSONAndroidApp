package com.example.dummyjsonapp.viewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.dummyjsonapp.MyApplication
import com.example.dummyjsonapp.domain.NetworkModule
import com.example.dummyjsonapp.domain.category.CategoryService
import com.example.dummyjsonapp.domain.product.ProductService
import com.example.dummyjsonapp.entity.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        //MyApplication().database.categoryDao()
        //
    }

    fun getData() {
        getCategoryData()
        //
    }
    fun getCategoryData() {
        val retService : CategoryService = NetworkModule.getRetrofitInstance().create(CategoryService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retService.getCategories()
            val categories: ArrayList<String>? = response.body()
            response.body().let {
                val categoryList: ArrayList<Category> = ArrayList()
                for (res in it!!) {
                    val category = Category(res)
                    categoryList.add(category)

//                    MyApplication.database.categoryDao().insert(category)
                    // MyApplication
                    //
                }
                println("qq -> $response")
            }
            println("qq -> $response")
        }

        //
    }

    fun getProductData() {
        val retService : ProductService = NetworkModule.getRetrofitInstance().create(ProductService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retService.getProducts()

            val qq = response.body()
            println("qq -> $response")
        }

        //
    }

    fun navigatetoCategories() {
        //
    }
/*
    fun navigateto () {
        //
    }*/

    fun navigateto () {
        //
    }
}