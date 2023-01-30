package com.project.smolentsev.idleclick.domain.repo

import androidx.lifecycle.LiveData
import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.entity.DataItem

interface ItemListRepo {
    suspend fun getItem(dataItemId: Int): DataItem
    suspend fun editItem(dataItem: DataItem)
    fun getItemList(): LiveData<List<DataItem>>


    suspend fun editResChar(resCharacter: ResCharacter)
    suspend fun getResCharacter(resCharacterId: Int): ResCharacter
    fun getAllResCharacter(): LiveData<List<ResCharacter>>


}