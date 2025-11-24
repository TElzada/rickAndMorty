package com.geeks.rickandmorty.presentation.cartoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geeks.rickandmorty.data.api.CartoonApi
import com.geeks.rickandmorty.data.repository.CartoonRepositoryImpl
import com.geeks.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterDetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val api = retrofit.create(CartoonApi::class.java)
            val repository = CartoonRepositoryImpl(api)
            val useCase = GetCharacterByIdUseCase(repository)

            return CharacterDetailViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
