package com.project.smolentsev.idleclick.domain.usecase

import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class GetItemUseCase(private val repository: ItemListRepo) {
    suspend operator fun invoke(id: Int): DataItem {
        return repository.getItem(id)
    }
}