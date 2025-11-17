package com.geeks.rickandmorty.domain.repository

import com.geeks.rickandmorty.data.api.CharacterDto
import kotlinx.coroutines.flow.Flow

interface CartoonRepository {
    fun getCharacters(): Flow<List<CharacterDto>>
}
