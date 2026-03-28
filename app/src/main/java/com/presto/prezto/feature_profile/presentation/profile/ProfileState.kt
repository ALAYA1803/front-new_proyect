package com.presto.prezto.feature_profile.presentation.profile

import com.presto.prezto.feature_profile.domain.model.UserProfile

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: UserProfile? = null,
    val error: String? = null
)