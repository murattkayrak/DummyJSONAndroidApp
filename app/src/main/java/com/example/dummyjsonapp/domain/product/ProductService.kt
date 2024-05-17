package com.example.dummyjsonapp.domain.product

import com.example.dummyjsonapp.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProducts(): Response<ProductResponse>

}