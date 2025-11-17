package com.geeks.rickandmorty.presentation.cartoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.rickandmorty.data.api.CharacterDto
import com.geeks.rickandmorty.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartoonViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _charactersState = MutableStateFlow<List<CharacterDto>>(emptyList())
    val charactersState: StateFlow<List<CharacterDto>> = _charactersState

    fun loadCharacters() {
        viewModelScope.launch {
            getCharacterUseCase.getCharacters().collect { list ->
                _charactersState.value = list
            }
        }
    }
}
