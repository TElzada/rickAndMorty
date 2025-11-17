package com.geeks.rickandmorty.data.api

import retrofit2.http.GET

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)

data class CharacterResponse(
    val results: List<CharacterDto>
)

interface CartoonApi {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}
