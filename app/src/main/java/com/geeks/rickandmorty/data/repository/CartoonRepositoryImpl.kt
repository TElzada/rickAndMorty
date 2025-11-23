package com.geeks.rickandmorty.data.repository

import com.geeks.rickandmorty.core.Either
import com.geeks.rickandmorty.data.api.CartoonApi
import com.geeks.rickandmorty.data.mapper.toDomain
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.repository.CartoonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CartoonRepositoryImpl(
    private val api: CartoonApi
) : CartoonRepository {
    override fun getCharacters(): Flow<Either<String , List<Character>>> {
        return flow {
            try{
                val response = api.getCharacters()
                if(response.isSuccessful && response.body() != null){
                    response.body()?.let{ result ->
                        emit(Either.Right(result.results.toDomain()))
                    }
                }else{
                    emit(Either.Left(response.message()))
                }
            }catch (e: Exception){
                emit(Either.Left(e.localizedMessage?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }
}
