package com.geeks.rickandmorty.domain.repository

import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CartoonRepository {
    fun getCharacters(): Flow<Either<String , List<Character>>>
    fun getCharacterById(id: Int): Flow<Either<String, Character>>
}
