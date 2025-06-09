package com.example.svomnipro.domain.useCase.auth

import com.example.svomnipro.data.remote.dto.CharacterDetailDTO
import com.example.svomnipro.data.source.Resource
import com.example.svomnipro.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterById @Inject constructor(
    private val repository: LoginRepository,
) {
    operator fun invoke(characterId: Int): Flow<Resource<CharacterDetailDTO>> = flow {
        emit(Resource.Loading())
        val characterDetail = repository.getCharacterById(characterId)
        emit(Resource.Success(characterDetail))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Unknown error"))
    }
}