package com.presto.prezto.feature_explore.presentation.home

import com.presto.prezto.feature_explore.domain.model.Category
import com.presto.prezto.feature_explore.domain.model.Item

data class HomeState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val featuredItems: List<Item> = emptyList(),
    val error: String? = null
)