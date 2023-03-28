package com.arincatlamaz.rickandmortyapp

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET(value = "character/1")
    fun getCharacter() : Call<GetCharacterResponse>
}