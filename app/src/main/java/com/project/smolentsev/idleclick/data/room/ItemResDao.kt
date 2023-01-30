package com.project.smolentsev.idleclick.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.entity.ResCharacter

@Dao
interface ItemResDao {

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItem(id: Int): DataItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editItem(dataItem: DataItem)

    @Query("SELECT * FROM item")
    fun getItemList(): LiveData<List<DataItem>>

    @Query("SELECT * FROM resource WHERE id = :id")
    suspend fun getResCharacter(id: Int): ResCharacter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editResChar(resCharacter: ResCharacter)

    @Query("SELECT * FROM resource")
    fun getAllResCharacter(): LiveData<List<ResCharacter>>

}