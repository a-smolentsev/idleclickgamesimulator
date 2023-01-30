package com.project.smolentsev.idleclick.domain.usecase

import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class EditCharUseCase(private val repository: ItemListRepo) {
    suspend operator fun invoke(resCharacter: ResCharacter){
        repository.editResChar(resCharacter)
    }
}