package com.example.svomnipro.presentation.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.svomnipro.presentation.dashboard.SignInScreen
import com.example.svomnipro.presentation.dashboard.DashboardViewModel
import com.example.svomnipro.presentation.characterDetail.ContingencyScreen
import com.example.svomnipro.presentation.characterDetail.CharacterDetailViewModel

@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.AUTH
    ) {
        composable(route = Graph.AUTH) {
            val viewModel: DashboardViewModel = hiltViewModel()
            val context = LocalContext.current
            val activity = (LocalContext.current as? Activity)
            SignInScreen(
                viewModel = viewModel,
                context = context,
                activity = activity,
                onCloseApp = {
                    activity?.finishAffinity()
                },
                onSelectCharacter = { idCharacter ->
                    val route = Routes.CharacterDetail.route + "/${idCharacter}"
                    navHostController.navigate(route = route)
                },
                navController = navHostController,
                onEvent = viewModel::onEvent,
            )
        }

        composable(Routes.CharacterDetail.route + "/{characterId}") {
            val viewModel: CharacterDetailViewModel = hiltViewModel()
            ContingencyScreen(
                navController = navHostController,
                viewModel = viewModel,
                onEvent = viewModel::onEvent
            )
        }
    }
}

object Graph {
    const val AUTH = "auth_graph"
}