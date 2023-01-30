package com.project.smolentsev.idleclick.domain.usecase

import androidx.lifecycle.LiveData
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class GetItemListUseCase(private val repository: ItemListRepo) {
    operator fun invoke():LiveData<List<DataItem>>{
        return repository.getItemList()
    }
}