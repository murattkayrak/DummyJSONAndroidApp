package com.example.dummyjsonapp.entity

import androidx.room.Entity

@Entity(tableName = "categories")
data class Category(
    var name: String?,
)
