package com.project.smolentsev.idleclick.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "resource")
data class ResCharacter(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: Long
)  : Serializable