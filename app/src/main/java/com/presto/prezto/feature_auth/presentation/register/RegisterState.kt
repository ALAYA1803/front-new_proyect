package com.presto.prezto.feature_auth.presentation.register

data class RegisterState(
    val fullName: String = "",
    val fullNameError: String? = null,
    val dni: String = "",
    val dniError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)