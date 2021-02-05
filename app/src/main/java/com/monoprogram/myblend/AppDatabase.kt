package com.monoprogram.myblend

import androidx.room.Database
import androidx.room.RoomDatabase
import com.monoprogram.myblend.entity.Blend
import com.monoprogram.myblend.entity.Herb
import com.monoprogram.myblend.repository.BlendDao
import com.monoprogram.myblend.repository.HerbDao

@Database(entities = [Herb::class, Blend::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun herbDao(): HerbDao
    abstract fun blendDao(): BlendDao
}
