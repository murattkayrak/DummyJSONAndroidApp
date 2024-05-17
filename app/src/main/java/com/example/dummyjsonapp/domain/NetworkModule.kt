package com.example.dummyjsonapp.domain

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    companion object {
        val BASE_URL = "https://dummyjson.com"

        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
        }
    }

}