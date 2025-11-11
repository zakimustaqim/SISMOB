package com.example.sismob.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sismob.domain.use_case.CheckLoginStatusUseCase
import com.example.sismob.domain.use_case.CheckPermissionsUseCase
import com.example.sismob.domain.use_case.SendDeviceDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkPermissionsUseCase: CheckPermissionsUseCase,
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val sendDeviceDetailsUseCase: SendDeviceDetailsUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _splashMessage = MutableStateFlow("Initializing...")
    val splashMessage = _splashMessage.asStateFlow()

    init {
        startSplashScreenLogic()
    }

    private fun startSplashScreenLogic() {
        viewModelScope.launch {
            _splashMessage.value = "Memeriksa Izin..."
            if (!checkPermissionsUseCase()) {
                _splashMessage.value = "Izin ditolak. Silakan berikan izin aplikasi melalui pengaturan."
                // In a real app, you'd navigate to an error screen or show a persistent dialog.
                // For now, we halt execution.
                return@launch
            }
            _splashMessage.value = "Izin Diberikan ✅"

            // You can add other checks here and update the message accordingly
            // _splashMessage.value = "Memeriksa Koneksi Internet..."
            // ...

            _splashMessage.value = "Mengirim Informasi Perangkat..."
            sendDeviceDetailsUseCase().onSuccess {
                _splashMessage.value = "Mengirim Informasi Perangkat ✅"
            }.onFailure {
                _splashMessage.value = "Gagal Mengirim Informasi Perangkat ❌"
                // Handle error appropriately
                return@launch
            }


            if (checkLoginStatusUseCase().first()) {
                _eventFlow.emit(UiEvent.Navigate("main"))
            } else {
                _eventFlow.emit(UiEvent.Navigate("login"))
            }
        }
    }

    sealed class UiEvent {
        data class Navigate(val route: String) : UiEvent()
    }
}
