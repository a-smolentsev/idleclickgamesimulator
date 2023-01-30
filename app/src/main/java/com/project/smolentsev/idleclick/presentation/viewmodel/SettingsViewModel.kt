package com.project.smolentsev.idleclick.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.smolentsev.idleclick.domain.ClearProgress
import com.project.smolentsev.idleclick.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
    fun clearProgress(){
        viewModelScope.launch {
            for(item in ClearProgress.initProgressData())
                useCases.editItemUseCase(item)
            for(resource in ClearProgress.initProgressChar())
                useCases.editCharUseCase(resource)
        }
    }
}