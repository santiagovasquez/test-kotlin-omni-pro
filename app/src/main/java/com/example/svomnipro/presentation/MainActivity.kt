package com.example.svomnipro.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.svomnipro.presentation.navigation.RootNavigationGraph
import com.example.svomnipro.presentation.ui.theme.ThemeAppOmniProTest
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ThemeAppOmniProTest {
                    Surface(
                        modifier = Modifier.Companion.fillMaxSize(),
                        color = Color.Companion.White
                    ) {
                        RootNavigationGraph(navHostController = rememberNavController())
                    }
                }
            }
        }
    }
}