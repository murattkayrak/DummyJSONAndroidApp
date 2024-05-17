package com.example.dummyjsonapp.domain.category

import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {

    @GET("/products/categories")
    suspend fun getCategories(): Response<ArrayList<String>>

}