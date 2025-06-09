package com.example.svomnipro.domain.repository

import com.example.svomnipro.data.remote.dto.CharacterDetailDTO
import com.example.svomnipro.data.remote.dto.CharacterResponseDTO

interface LoginRepository {
    suspend fun getCharacters(page: Int): CharacterResponseDTO
    suspend fun getCharacterById(characterId: Int): CharacterDetailDTO
}