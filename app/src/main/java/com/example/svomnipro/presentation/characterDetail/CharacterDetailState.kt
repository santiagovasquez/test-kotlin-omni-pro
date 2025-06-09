package com.example.svomnipro.presentation.characterDetail

import com.example.svomnipro.data.remote.dto.CharacterDetailDTO
import com.example.svomnipro.presentation.components.snackbar.TypeSnackBar

data class CharacterDetailState(
    var showSnack: Boolean = false,
    var showSnackDialog: Boolean = false,
    var typeSnackBar: TypeSnackBar? = null,
    val loading: Boolean = false,
    val characterDetail: CharacterDetailDTO? = null,
)

