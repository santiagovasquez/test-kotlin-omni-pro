package com.example.svomnipro.presentation.signin

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.ImageBitmap

data class SignInState(
    val showDialog: Boolean = false,
    val serial: String = "",
    val deviceId: String = "",
    val fieldEditable: Boolean = true,
    val onUpdate: Boolean = false,
    val firstLogin: Boolean = false,
    val showDialogBarCode: Boolean = false,
    val barCode: ImageBitmap? = null,
    val showToast: Boolean = false,
    @StringRes val messageToast: Int? = null,
    val showDialogError: Boolean = false,
    val showCheck: Boolean = false,
    val isContingency: Boolean = false,
    val enableContingency: Boolean = false,
    val enableResilience: Boolean = false,
    val userStatic: String = "",
    val passwordStatic: String = "",
)
