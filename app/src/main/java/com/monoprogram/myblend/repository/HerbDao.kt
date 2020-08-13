package com.monoprogram.myblend.repository

import androidx.room.*
import com.monoprogram.myblend.entity.Herb

@Dao
interface HerbDao {
    @Insert
    fun insert(user: Herb)

    @Update
    fun update(user: Herb)

    @Delete
    fun delete(user: Herb)

    @Query("delete from herb")
    fun deleteAll()

    @Query("select * from herb")
    fun getAll(): List<Herb>

    @Query("select * from herb where name = :name")
    fun getHerb(name: String): Herb
}
