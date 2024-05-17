package com.example.dummyjsonapp.datasource

import com.example.dummyjsonapp.dao.CategoryDao
import com.example.dummyjsonapp.entity.Category
import kotlinx.coroutines.flow.Flow

class CategoryLocalSource(private val categoryDao: CategoryDao) {

    fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAll()
    }

    fun insert(category: Category) {
        categoryDao.insert(category)
    }

    fun delete(nameList: List<String>) {
        categoryDao.delete(nameList)
    }
}