package com.monoprogram.myblend.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blend")
data class Blend(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val blendName: String,
    @ColumnInfo(name = "herbName") val herbName: String,
    @ColumnInfo(name = "herbValue") val herbValue: String,
    @ColumnInfo(name = "blendImage") val blendImage: Int,
    @ColumnInfo(name = "blendDescription") val blendDescription: String
)