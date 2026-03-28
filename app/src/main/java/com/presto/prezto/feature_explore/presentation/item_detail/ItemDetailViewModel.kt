package com.presto.prezto.feature_explore.presentation.item_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presto.prezto.feature_explore.domain.usecase.GetItemByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val getItemByIdUseCase: GetItemByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ItemDetailState())
    val state: StateFlow<ItemDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("itemId")?.let { id ->
            loadItem(id)
        } ?: run {
            _state.update { it.copy(isLoading = false, error = "ID de herramienta no encontrado") }
        }
    }

    private fun loadItem(itemId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                val result = getItemByIdUseCase(itemId)

                if (result != null) {
                    _state.update { it.copy(isLoading = false, item = result) }
                } else {
                    _state.update { it.copy(isLoading = false, error = "La herramienta ya no está disponible") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = "Error de conexión: ${e.message}") }
            }
        }
    }
}