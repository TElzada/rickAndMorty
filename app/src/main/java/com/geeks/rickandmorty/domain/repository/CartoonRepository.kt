package com.geeks.rickandmorty.domain.repository

import kotlinx.coroutines.flow.Flow

interface CartoonRepository{
    fun getCharacters(): Flow<List<String>>
}


