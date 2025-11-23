package com.geeks.rickandmorty.presentation.cartoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CartoonViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _charactersState = MutableStateFlow<List<Character>>(emptyList())
    val charactersState: StateFlow<List<Character>> = _charactersState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadCharacters() {
        viewModelScope.launch {
            getCharacterUseCase
                .getCharacters()
                .onStart {
                    _isLoading.value = true
                    _error.value = null
                }
                .collect { result ->
                    _isLoading.value = false
                    when (result) {
                        is Either.Left -> {
                            _error.value = result.value
                        }
                        is Either.Right -> {
                            _charactersState.value = result.value
                        }
                    }
                }
        }
    }
}
