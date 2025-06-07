package com.example.svomnipro.presentation.characterDetail

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svomnipro.data.source.Resource
import com.example.svomnipro.domain.useCase.auth.AuthUseCase
import com.example.svomnipro.presentation.components.snackbar.TypeSnackBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.svomnipro.R

@HiltViewModel
class characterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _state: MutableState<characterDetailState> = mutableStateOf(characterDetailState())
    val state: State<characterDetailState> = _state
    private val characterId = savedStateHandle.get<String>("characterId")

    init {
        viewModelScope.launch {
            if (!characterId.isNullOrEmpty()) {
             getCharacterById(characterId.toInt())
            }
        }
    }

    fun onEvent(event: characterDetailEvent) {
        when (event) {
            is characterDetailEvent.OnClearSnack -> {
                _state.value = state.value.copy(showSnack = false, typeSnackBar = null)
            }
        }
    }

    private fun getCharacterById(characterId: Int) {
        authUseCase.getCharacterById.invoke(characterId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        loading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        loading = false
                    )
                    onErrorResource(R.string.get_api_error)
                }

                is Resource.Success -> {
                    resource.data?.let { result ->
                        _state.value = state.value.copy(
                            characterDetail = result,
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

}