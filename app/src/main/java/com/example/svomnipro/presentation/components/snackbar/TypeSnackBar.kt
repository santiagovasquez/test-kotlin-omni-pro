package com.example.svomnipro.presentation.components.snackbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.svomnipro.presentation.ui.theme.Beige3
import com.example.svomnipro.presentation.ui.theme.GreenSuccess
import com.example.svomnipro.presentation.ui.theme.Secondary

sealed class TypeSnackBar {
    data class OnSuccess(
        @StringRes val message: Int,
        val color: Color = GreenSuccess,
        val icon: ImageVector = Icons.Default.Done
    ) : TypeSnackBar()

    data class OnSuccessMessage(
        val message: String,
        val color: Color = GreenSuccess,
        val icon: ImageVector = Icons.Default.Done
    ) : TypeSnackBar()

    data class OnErrorMessageResource(
        @StringRes val message: Int,
        val color: Color = Color.Red,
        val icon: ImageVector = Icons.Default.Close
    ) : TypeSnackBar()

    data class OnWarning(
        @StringRes val message: Int,
        val color: Color = Beige3,
        val icon: ImageVector = Icons.Default.Warning
    ) : TypeSnackBar()

    data class OnInfo(
        @StringRes val message: Int,
        val color: Color = Secondary,
        val icon: ImageVector = Icons.Default.Info
    ) : TypeSnackBar()

    data class OnErrorMessage(
        val message: String,
        val color: Color = Color.Red,
        val icon: ImageVector = Icons.Default.Close
    ) : TypeSnackBar()
}