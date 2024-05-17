package com.example.dummyjsonapp.response

import com.example.dummyjsonapp.entity.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val products: List<Product>?,
    val total: Int?,
    val skip: Int?,
    val limit: Int?,
)
