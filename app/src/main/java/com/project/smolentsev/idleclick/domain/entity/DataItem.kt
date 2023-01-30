package com.project.smolentsev.idleclick.domain.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "item")
data class DataItem(
    @ColumnInfo(name = "id")  @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "price") val price: Long,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "incomeNextLvl") val incomeNextLevel: Int
)  : Serializable