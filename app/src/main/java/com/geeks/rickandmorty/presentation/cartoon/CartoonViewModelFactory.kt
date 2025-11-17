package com.geeks.rickandmorty.presentation.cartoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geeks.rickandmorty.data.api.CartoonApi
import com.geeks.rickandmorty.data.api.NetworkModule
import com.geeks.rickandmorty.data.repository.CartoonRepositoryImpl
import com.geeks.rickandmorty.domain.usecases.GetCharacterUseCase

class CartoonViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api = NetworkModule.retrofit.create(CartoonApi::class.java)
        val repo = CartoonRepositoryImpl(api)
        val useCase = GetCharacterUseCase(repo)
        return CartoonViewModel(useCase) as T
    }
}
