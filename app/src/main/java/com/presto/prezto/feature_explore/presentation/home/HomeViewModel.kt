package com.presto.prezto.feature_explore.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presto.prezto.feature_explore.domain.usecase.GetCategoriesUseCase
import com.presto.prezto.feature_explore.domain.usecase.GetFeaturedItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getFeaturedItemsUseCase: GetFeaturedItemsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .onStart {
                    _state.update { it.copy(isLoading = true, error = null) }
                }
                .catch { exception ->
                    _state.update { it.copy(isLoading = false, error = exception.message) }
                }
                .collect { categoriesList ->
                    _state.update { it.copy(categories = categoriesList) }
                }
            getFeaturedItemsUseCase()
                .catch { exception ->
                    _state.update { it.copy(isLoading = false, error = exception.message) }
                }
                .collect { itemsList ->
                    _state.update { it.copy(featuredItems = itemsList, isLoading = false) }
                }
        }
    }
}