package com.project.smolentsev.idleclick.data.room.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.data.room.ItemResDao
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo


class ItemResRepoImpl(private val dao: ItemResDao): ItemListRepo {

    override suspend fun getItem(dataItemId: Int): DataItem {
        return dao.getItem(dataItemId)
    }

    override suspend fun editItem(dataItem: DataItem) {
       return dao.editItem(dataItem)
    }

    override fun getItemList(): LiveData<List<DataItem>> {

        return dao.getItemList()
    }

    override suspend fun editResChar(resCharacter: ResCharacter) {
        return dao.editResChar(resCharacter)
    }

    override suspend fun getResCharacter(resCharacterId: Int): ResCharacter {
        return dao.getResCharacter(resCharacterId)
    }

    override fun getAllResCharacter(): LiveData<List<ResCharacter>> {
        return dao.getAllResCharacter()
    }
}