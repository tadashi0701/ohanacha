package com.monoprogram.myblend.repository

import androidx.room.*
import com.monoprogram.myblend.entity.Blend

@Dao
interface BlendDao {
    @Insert
    fun insert(blend: Blend)

    @Update
    fun update(blend: Blend)

    @Delete
    fun delete(blend: Blend)

    @Query("delete from blend")
    fun deleteAll()

    @Query("select * from blend")
    fun getAll(): List<Blend>

    @Query("select * from blend where name = :name")
    fun getBlend(name: String): Blend
}
