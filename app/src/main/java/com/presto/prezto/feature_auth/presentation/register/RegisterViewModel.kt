package com.presto.prezto.feature_auth.presentation.register

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
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()
    fun onFullNameChanged(name: String) = _state.update { it.copy(fullName = name, fullNameError = null) }
    fun onDniChanged(dni: String) {
        if (dni.all { it.isDigit() } && dni.length <= 8) {
            _state.update { it.copy(dni = dni, dniError = null) }
        }
    }

    fun onPhoneChanged(phone: String) {
        if (phone.all { it.isDigit() } && phone.length <= 9) {
            _state.update { it.copy(phone = phone, phoneError = null) }
        }
    }

    fun onEmailChanged(email: String) = _state.update { it.copy(email = email, emailError = null) }
    fun onPasswordChanged(password: String) = _state.update { it.copy(password = password, passwordError = null) }

    fun register() {
        val s = _state.value
        var hasError = false
        if (s.fullName.trim().length < 3) {
            _state.update { it.copy(fullNameError = "Ingresa tu nombre completo") }
            hasError = true
        }
        if (s.dni.length != 8) {
            _state.update { it.copy(dniError = "El DNI debe tener 8 dígitos") }
            hasError = true
        }
        if (s.phone.length != 9) {
            _state.update { it.copy(phoneError = "Ingresa un celular válido") }
            hasError = true
        }
        if (s.email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(s.email).matches()) {
            _state.update { it.copy(emailError = "Ingresa un correo válido") }
            hasError = true
        }
        if (s.password.length < 6) {
            _state.update { it.copy(passwordError = "Mínimo 6 caracteres") }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            delay(2000)
            _state.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}