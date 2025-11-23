package com.geeks.rickandmorty.domain.usecases

import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CartoonRepository
) {
    fun getCharacters(): Flow<Either<String , List<Character>>> = repository.getCharacters()
}
