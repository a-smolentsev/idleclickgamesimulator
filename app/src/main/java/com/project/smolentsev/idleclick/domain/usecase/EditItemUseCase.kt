package com.project.smolentsev.idleclick.domain.usecase

import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class EditItemUseCase(private val repository: ItemListRepo) {
    suspend operator fun invoke(dataItem: DataItem){
        repository.editItem(dataItem)
    }
}