package com.geeks.rickandmorty.domain.usecases

import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow
class GetCharacterByIdUseCase(
    private val repository: CartoonRepository
) {
    fun getCharacter(id: Int): Flow<Either<String, Character>> =
        repository.getCharacterById(id)
}
