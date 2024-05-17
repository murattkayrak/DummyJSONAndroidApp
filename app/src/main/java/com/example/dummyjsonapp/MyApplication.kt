package com.example.dummyjsonapp

import android.app.Application
import androidx.room.Room
import com.example.dummyjsonapp.database.AppDatabase
import com.example.dummyjsonapp.database.DBHelper

class MyApplication : Application() {
    /*
    val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "myapp.db").build()
    }
    */

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "myapp.db").build()
    }
}