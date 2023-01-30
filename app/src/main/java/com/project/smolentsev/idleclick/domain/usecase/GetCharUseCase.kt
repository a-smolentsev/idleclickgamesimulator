package com.project.smolentsev.idleclick.domain.usecase

import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class GetCharUseCase(private val repository: ItemListRepo) {
    suspend operator fun invoke(id: Int): ResCharacter {
        return repository.getResCharacter(id)
    }
}