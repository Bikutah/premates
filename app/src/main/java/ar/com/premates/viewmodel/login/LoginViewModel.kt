package ar.com.premates.viewmodel.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class LoginUiState {
    object Iniciado : LoginUiState()
    object Compleado : LoginUiState()
    data class Error(val mensaje: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Iniciado)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(usuario: String, password: String) {
        when {
            usuario.isBlank() || password.isBlank() -> {
                _uiState.value = LoginUiState.Error("Completá todos los campos")
            }
            usuario == "admin" && password == "123456" -> {
                _uiState.value = LoginUiState.Compleado
            }
            else -> {
                _uiState.value = LoginUiState.Error("Usuario o contraseña incorrectos")
            }
        }
    }
}