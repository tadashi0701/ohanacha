package com.monoprogram.myblend.repository

import androidx.room.*
import com.monoprogram.myblend.entity.Blend

@Dao
interface BlendDao {
    @Insert
    fun insert(user: Blend)

    @Update
    fun update(user: Blend)

    @Delete
    fun delete(user: Blend)

    @Query("delete from blend")
    fun deleteAll()

    @Query("select * from blend")
    fun getAll(): List<Blend>

    @Query("select * from blend where name = :name")
    fun getBlend(name: String): Blend
}
