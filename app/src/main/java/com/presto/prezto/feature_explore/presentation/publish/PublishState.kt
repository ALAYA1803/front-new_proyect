package com.presto.prezto.feature_explore.presentation.publish

data class PublishState(
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val dailyRate: String = "",
    val dailyRateError: String? = null,
    val condition: String = "Bueno",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)