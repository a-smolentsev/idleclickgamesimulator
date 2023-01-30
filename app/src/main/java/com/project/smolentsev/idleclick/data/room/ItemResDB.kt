package com.project.smolentsev.idleclick.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.entity.ResCharacter

@Database(
    entities = [ResCharacter::class, DataItem::class],
    version = 1,
    exportSchema = false
)
abstract class ItemResDB: RoomDatabase() {

    abstract val itemResDao: ItemResDao

    companion object {
        const val DATABASE_NAME = const.DATABASE_NAME
    }
}