package com.geeks.rickandmorty.data.repository

import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.data.api.CartoonApi
import com.geeks.rickandmorty.data.base.BaseRepository
import com.geeks.rickandmorty.data.mapper.toDomain
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartoonRepositoryImpl(
    private val api: CartoonApi
) : CartoonRepository, BaseRepository() {

    override fun getCharacters(): Flow<Either<String, List<Character>>> {
        return doRequest { api.getCharacters() }
            .map { either ->
                when (either) {
                    is Either.Left -> Either.Left(either.value)
                    is Either.Right -> {
                        val dto = either.value
                        val list = dto.results?.toDomain() ?: emptyList()
                        Either.Right(list)
                    }
                }
            }
    }

    override fun getCharacterById(id: Int): Flow<Either<String, Character>> {
        return doRequest { api.getCharacterById(id) }
            .map { either ->
                when (either) {
                    is Either.Left -> Either.Left(either.value)
                    is Either.Right -> {
                        val dto = either.value
                        Either.Right(dto.toDomain())
                    }
                }
            }
    }
}





