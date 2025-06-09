package com.example.svomnipro.data.repository

import com.example.svomnipro.data.remote.dto.CharacterDetailDTO
import com.example.svomnipro.data.remote.dto.CharacterResponseDTO
import com.example.svomnipro.data.source.RestDataSource
import com.example.svomnipro.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val resDataSource: RestDataSource
) : LoginRepository {
    override suspend fun getCharacters(page: Int): CharacterResponseDTO {
        return resDataSource.getCharacters(page)
    }

    override suspend fun getCharacterById(characterId: Int): CharacterDetailDTO {
        return resDataSource.getCharacterById(characterId)
    }
}