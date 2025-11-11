package com.example.sismob.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sismob.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).onSuccess {
                _eventFlow.emit(UiEvent.LoginSuccess)
            }
        }
    }

    sealed class UiEvent {
        object LoginSuccess : UiEvent()
    }
}
