package com.geeks.rickandmorty.domain.usecases

import com.geeks.rickandmorty.data.api.CharacterDto
import com.geeks.rickandmorty.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CartoonRepository
) {
    fun getCharacters(): Flow<List<CharacterDto>> = repository.getCharacters()
}
