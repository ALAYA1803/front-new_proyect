package com.presto.prezto.feature_profile.domain.model

data class UserProfile(
    val id: String,
    val fullName: String,
    val location: String,
    val trustScore: Double,
    val isDniVerified: Boolean,
    val isPhoneVerified: Boolean,
    val activeRentals: Int,
    val toolsPublished: Int
)