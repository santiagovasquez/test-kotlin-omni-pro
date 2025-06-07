package com.example.svomnipro.domain.useCase.auth

import com.example.svomnipro.data.remote.dto.CharacterResponseDTO
import com.example.svomnipro.data.source.Resource
import com.example.svomnipro.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: LoginRepository,
) {
    operator fun invoke(page: Int = 1): Flow<Resource<CharacterResponseDTO>> = flow {
        emit(Resource.Loading())
        val characters = repository.getCharacters(page)
        emit(Resource.Success(characters))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "Unknown error"))
    }
}