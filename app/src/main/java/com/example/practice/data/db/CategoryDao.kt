package com.example.practice.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practice.model.Category
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryDao {
    @Query("SELECT * from category")
    fun getAll(): Single<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: List<Category>): Completable
}
