package com.example.svomnipro.presentation.components.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun CustomSnackBar(type: TypeSnackBar, onHideSnack: (() -> Unit)? = null) {
    when (type) {
        is TypeSnackBar.OnSuccess -> snackBarContent(
            message = stringResource(id = type.message),
            color = type.color,
            icon = type.icon,
            onHideSnack = onHideSnack
        )

        is TypeSnackBar.OnSuccessMessage -> snackBarContent(
            message = type.message,
            color = type.color,
            icon = type.icon,
            onHideSnack = onHideSnack
        )

        is TypeSnackBar.OnErrorMessageResource -> snackBarContent(
            message = stringResource(id = type.message),
            color = type.color,
            icon = type.icon,
            onHideSnack = onHideSnack
        )

        is TypeSnackBar.OnWarning -> snackBarContent(
            message = stringResource(id = type.message),
            color = type.color,
            icon = type.icon,
            onHideSnack = onHideSnack
        )

        is TypeSnackBar.OnInfo -> snackBarContent(
            message = stringResource(id = type.message),
            color = type.color,
            icon = type.icon,
            onHideSnack = onHideSnack
        )

        is TypeSnackBar.OnErrorMessage -> {
            snackBarContent(
                message = type.message,
                color = type.color,
                icon = type.icon,
                onHideSnack = onHideSnack
            )
        }
    }
}