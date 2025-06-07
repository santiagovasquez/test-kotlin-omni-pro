package com.example.svomnipro.data.source

import com.example.svomnipro.data.remote.dto.CharacterDetailDTO
import com.example.svomnipro.data.remote.dto.CharacterResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestDataSource {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int = 1): CharacterResponseDTO

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDetailDTO
}
