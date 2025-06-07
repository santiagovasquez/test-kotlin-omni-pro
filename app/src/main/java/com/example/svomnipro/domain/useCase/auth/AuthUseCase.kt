package com.example.svomnipro.domain.useCase.auth

data class AuthUseCase(
    val getCharacters: GetCharacters,
    val getCharacterById: GetCharacterById
)