package com.geeks.rickandmorty.data.base

import com.geeks.rickandmorty.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {
    protected fun <T> doRequest(
        request: suspend () -> Response<T>
    ): Flow<Either<String, T>> {
        return flow {
            try {
                val response = request()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    emit(Either.Right(body))
                } else {
                    emit(Either.Left(response.message()))
                }
            } catch (e: Exception) {
                emit(Either.Left(e.localizedMessage ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }
}
