package com.monoprogram.myblend.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "herb")
data class Herb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val herbName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_resource") val imageId: Int
)