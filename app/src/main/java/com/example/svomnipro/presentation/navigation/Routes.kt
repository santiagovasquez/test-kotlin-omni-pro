package com.example.svomnipro.presentation.navigation

import com.example.svomnipro.presentation.navigation.ConstantsRoute.CHARACTER_DETAIL

sealed class Routes(
    val route: String,
    val title: String = ""
) {
    object CharacterDetail : Routes(
        route = CHARACTER_DETAIL,
        title = "Lista personajes"
    )
}