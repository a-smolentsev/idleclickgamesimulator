package com.project.smolentsev.idleclick.domain.usecase

import androidx.lifecycle.LiveData
import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.repo.ItemListRepo

class GetCharListUseCase(private val repository: ItemListRepo) {
    operator fun invoke():LiveData<List<ResCharacter>>{
        return repository.getAllResCharacter()
    }
}