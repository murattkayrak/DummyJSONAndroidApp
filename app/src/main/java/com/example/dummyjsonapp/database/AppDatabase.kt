package com.example.dummyjsonapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dummyjsonapp.dao.CategoryDao
import com.example.dummyjsonapp.dao.ProductDao
import com.example.dummyjsonapp.dao.ProductImagesDao
import com.example.dummyjsonapp.entity.Category
import com.example.dummyjsonapp.entity.Product
import com.example.dummyjsonapp.entity.ProductImages

//Create a Room database class that includes the entity and DAO:
@Database(
    entities = [
        Category::class
//        ,
//        Product::class,
//        ProductImages::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun productImagesDao(): ProductImagesDao
}