package com.example.dummyjsonapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dummyjsonapp.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAll(): Flow<List<Category>>

    @Insert
    fun insert(vararg movieItems: Category)

    @Query("DELETE FROM categories WHERE name IN (:nameList)")
    fun delete(nameList: List<String>)

//    @Query("UPDATE movie_table SET title = :newTitle WHERE id = :movieId")
//    suspend fun updateTitle(movieId: Int, newTitle: String)
}