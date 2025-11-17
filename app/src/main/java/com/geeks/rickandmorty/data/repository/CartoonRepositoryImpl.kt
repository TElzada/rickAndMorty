package com.geeks.rickandmorty.data.repository

import com.geeks.rickandmorty.data.api.CartoonApi
import com.geeks.rickandmorty.data.api.CharacterDto
import com.geeks.rickandmorty.domain.repository.CartoonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CartoonRepositoryImpl(
    private val api: CartoonApi
) : CartoonRepository {
    override fun getCharacters(): Flow<List<CharacterDto>> = flow {
        val response = api.getCharacters()
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}
