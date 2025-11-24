package com.geeks.rickandmorty.data.api

import com.geeks.rickandmorty.data.models.BaseResponseDto
import com.geeks.rickandmorty.data.models.CharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApi {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponseDto>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<CharacterDto>
}
