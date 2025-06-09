package com.example.svomnipro.presentation.dashboard

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.svomnipro.data.source.Resource
import com.example.svomnipro.domain.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.svomnipro.R
import com.example.svomnipro.presentation.components.snackbar.TypeSnackBar
import com.example.svomnipro.presentation.dashboard.DashboardEvent.OnClearSnack

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> = _state
    val navController: MutableState<NavController?> = mutableStateOf(null)
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        getCharacters()
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is OnClearSnack -> {
                _state.value = state.value.copy(showSnack = false, typeSnackBar = null)
            }
        }
    }

    fun loadNextPage() {
        val nextPage = state.value.currentPage + 1
        getCharacters(nextPage)
    }

    private fun getCharacters(page: Int = state.value.currentPage) {
        authUseCase.getCharacters.invoke(page).onEach { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Error -> {
                    hideLoading()
                    onErrorResource(R.string.get_api_error)
                }
                is Resource.Success -> {
                    resource.data?.let { result ->
                        _state.value = state.value.copy(
                            characters = state.value.characters + result.results,
                            currentPage = page,
                            loading = false
                        )

                        onSuccess(R.string.get_api_success)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun onErrorResource(@StringRes message: Int) {
        _state.value = state.value.copy(
            typeSnackBar = TypeSnackBar.OnErrorMessageResource(message),
            showSnack = true
        )
    }

    fun onSuccess(@StringRes message: Int) {
        _state.value = state.value.copy(
            typeSnackBar = TypeSnackBar.OnSuccess(message),
            showSnack = true
        )
    }

    private fun showLoading() {
        _state.value = state.value.copy(loading = true)
    }

    private fun hideLoading() {
        _state.value = state.value.copy(loading = false)
    }
}