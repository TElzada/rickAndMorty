package com.geeks.rickandmorty.data.models

import com.google.gson.annotations.SerializedName
data class BaseResponseDto(
    @SerializedName("info")
    val info: InfoDto? = null,
    @SerializedName("results")
    val results: List<CharacterDto>? = null
)