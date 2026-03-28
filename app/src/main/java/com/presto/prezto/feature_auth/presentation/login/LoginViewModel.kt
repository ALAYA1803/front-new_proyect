package com.presto.prezto.feature_auth.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()
    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email, emailError = null, error = null) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password, passwordError = null, error = null) }
    }

    fun login() {
        val email = _state.value.email
        val password = _state.value.password
        var hasError = false

        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.update { it.copy(emailError = "Ingresa un correo válido") }
            hasError = true
        }
        if (password.isBlank() || password.length < 6) {
            _state.update { it.copy(passwordError = "La contraseña debe tener al menos 6 caracteres") }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            delay(1500)

            if (password == "123456") {
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Credenciales incorrectas. Para probar usa la clave: 123456"
                    )
                }
            }
        }
    }
}