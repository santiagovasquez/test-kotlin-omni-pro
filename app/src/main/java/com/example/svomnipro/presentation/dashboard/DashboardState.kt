package com.example.svomnipro.presentation.dashboard

import androidx.annotation.StringRes
import com.example.svomnipro.data.remote.dto.CharacterDTO
import com.example.svomnipro.presentation.components.snackbar.TypeSnackBar

data class DashboardState(
    val showDialog: Boolean = false,
    @StringRes val messageToast: Int? = null,
    val characters: List<CharacterDTO> = emptyList(),
    val loading: Boolean = false,
    val typeSnackBar: TypeSnackBar? = null,
    val showSnack: Boolean = false,
    val currentPage: Int = 1,
)
