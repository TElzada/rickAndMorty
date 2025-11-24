package com.geeks.rickandmorty.presentation.cartoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _characterState = MutableStateFlow<Character?>(null)
    val characterState: StateFlow<Character?> = _characterState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase
                .getCharacter(id)
                .onStart {
                    _isLoading.value = true
                    _error.value = null
                }
                .collect { result ->
                    _isLoading.value = false
                    when (result) {
                        is Either.Left -> _error.value = result.value
                        is Either.Right -> _characterState.value = result.value
                    }
                }
        }
    }
}
