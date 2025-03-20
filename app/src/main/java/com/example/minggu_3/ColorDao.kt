package com.example.minggu_3

import androidx.room.*

@Dao
interface ColorDao {
    @Query("SELECT * FROM Color")
    fun getAll(): List<Color>

    @Insert
    fun insert(vararg color: Color)

    @Update
    fun update(color: Color)

    @Delete
    fun delete(color: Color)
}