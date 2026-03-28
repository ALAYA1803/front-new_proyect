package com.presto.prezto.feature_explore.domain.model

enum class ItemCondition {
    NEW, GOOD, FAIR, NEEDS_REPAIR
}

data class Item(
    val id: String,
    val ownerId: String,
    val categoryId: String,
    val title: String,
    val description: String,
    val dailyRate: Double,
    val hourlyRate: Double,
    val currentCondition: ItemCondition,
    val isAvailable: Boolean,
    val imageUrl: String
)