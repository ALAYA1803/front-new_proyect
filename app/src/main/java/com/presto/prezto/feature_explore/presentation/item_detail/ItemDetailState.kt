package com.presto.prezto.feature_explore.presentation.item_detail

import com.presto.prezto.feature_explore.domain.model.Item

data class ItemDetailState(
    val isLoading: Boolean = true,
    val item: Item? = null,
    val error: String? = null
)