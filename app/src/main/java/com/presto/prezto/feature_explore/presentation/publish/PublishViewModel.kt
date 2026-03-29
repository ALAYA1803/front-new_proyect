package com.presto.prezto.feature_explore.presentation.publish

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
class PublishViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PublishState())
    val state: StateFlow<PublishState> = _state.asStateFlow()

    fun onTitleChanged(title: String) = _state.update { it.copy(title = title, titleError = null) }
    fun onDescriptionChanged(desc: String) = _state.update { it.copy(description = desc, descriptionError = null) }

    fun onDailyRateChanged(rate: String) {
        if (rate.isEmpty() || rate.matches(Regex("^\\d*\\.?\\d*$"))) {
            _state.update { it.copy(dailyRate = rate, dailyRateError = null) }
        }
    }

    fun publishTool() {
        val s = _state.value
        var hasError = false

        if (s.title.trim().length < 5) {
            _state.update { it.copy(titleError = "El título debe ser descriptivo (min. 5 letras)") }
            hasError = true
        }
        if (s.description.trim().length < 20) {
            _state.update { it.copy(descriptionError = "Da más detalles sobre la herramienta (min. 20 letras)") }
            hasError = true
        }
        if (s.dailyRate.isBlank() || s.dailyRate.toDoubleOrNull() == null || s.dailyRate.toDouble() <= 0) {
            _state.update { it.copy(dailyRateError = "Ingresa un precio válido mayor a 0") }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            delay(1500)
            _state.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}