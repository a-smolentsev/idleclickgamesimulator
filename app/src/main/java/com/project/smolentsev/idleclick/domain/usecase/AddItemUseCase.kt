package com.project.smolentsev.idleclick.domain.usecase

import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class AddItemUseCase(private val itemListRepo: ItemListRepo) {
    fun addItem(item: DataItem){
    }
}