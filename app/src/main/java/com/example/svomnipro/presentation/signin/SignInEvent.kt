package com.example.svomnipro.presentation.signin

import androidx.navigation.NavController

sealed class SignInEvent {
    data class LogIn(val navController: NavController) : SignInEvent()
    object OnShowDialogBarCode : SignInEvent()
    object OnDismissDialogBarCode : SignInEvent()
    object OnDismissDialog : SignInEvent()
    object OnCopySerialBarCode : SignInEvent()
    data class OnContingency(val isContingency: Boolean) : SignInEvent()
}