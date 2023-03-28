package com.arincatlamaz.rickandmortyapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyAPIService {
    private val BASE_URL = "https://rickandmortyapi.com/api/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)


    fun getData() : Call<GetCharacterResponse> {
        return api.getCharacter()
    }
}