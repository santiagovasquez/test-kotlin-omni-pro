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
import com.example.svomnipro.presentation.signin.SignInScreen
import com.example.svomnipro.presentation.signin.SignInViewModel

@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.AUTH
    ) {
        composable(route = Graph.AUTH) {
            val viewModel: SignInViewModel = hiltViewModel()
            val context = LocalContext.current
            val activity = (LocalContext.current as? Activity)
            SignInScreen(
                viewModel = viewModel,
                context = context,
                activity = activity,
                onCloseApp = {
                    activity?.finishAffinity()
                },
                navController = navHostController,
            )
        }
    }
}

object Graph {
    const val AUTH = "auth_graph"
}