package com.presto.prezto.feature_profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presto.prezto.feature_profile.domain.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        loadMockProfile()
    }

    private fun loadMockProfile() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(800)
            val mockUser = UserProfile(
                id = "usr_99",
                fullName = "Rodrigo A.",
                location = "San Miguel, Lima",
                trustScore = 4.9,
                isDniVerified = true,
                isPhoneVerified = true,
                activeRentals = 2,
                toolsPublished = 5
            )

            _state.update { it.copy(isLoading = false, profile = mockUser) }
        }
    }
}