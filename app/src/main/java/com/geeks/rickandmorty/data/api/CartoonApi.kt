package com.geeks.rickandmorty.data.api

import com.geeks.rickandmorty.data.models.BaseResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CartoonApi {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponseDto>
}
