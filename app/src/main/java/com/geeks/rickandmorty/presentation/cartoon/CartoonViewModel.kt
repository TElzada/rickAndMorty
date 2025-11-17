package com.geeks.rickandmorty.presentation.cartoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.rickandmorty.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartoonViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel(){
    private val _charactersState = MutableStateFlow<List<String>>(emptyList())
    val charactersState: StateFlow<List<String>> = _charactersState
    fun getCharacters(){
        viewModelScope.launch {
            getCharacterUseCase.getCharacters().collect{ data ->
                _charactersState.value = data

            }
        }

    }
}