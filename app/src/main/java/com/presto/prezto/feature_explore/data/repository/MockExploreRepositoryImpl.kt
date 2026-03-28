package com.presto.prezto.feature_explore.data.repository

import com.presto.prezto.feature_explore.domain.model.Category
import com.presto.prezto.feature_explore.domain.model.Item
import com.presto.prezto.feature_explore.domain.model.ItemCondition
import com.presto.prezto.feature_explore.domain.repository.ExploreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockExploreRepositoryImpl : ExploreRepository {
    // Categorías
    private val mockCategories = listOf(
        Category(id = "c1", name = "Herramientas Eléctricas"),
        Category(id = "c2", name = "Fotografía y Video"),
        Category(id = "c3", name = "Jardinería"),
        Category(id = "c4", name = "Construcción")
    )

    // Simulacion de herramientas
    private val mockItems = listOf(
        Item(
            id = "item_001",
            ownerId = "user_101",
            categoryId = "c1",
            title = "Taladro Percutor Bosch 750W",
            description = "Taladro percutor en perfecto estado, ideal para perforar concreto y madera. Incluye maletín y set de brocas básicas.",
            dailyRate = 25.0,
            hourlyRate = 5.0,
            currentCondition = ItemCondition.GOOD,
            isAvailable = true,
            imageUrl = "https://images.unsplash.com/photo-1504148455328-c376907d081c?q=80&w=800&auto=format&fit=crop"
        ),
        Item(
            id = "item_002",
            ownerId = "user_102",
            categoryId = "c2",
            title = "Cámara Sony Alpha a7 III",
            description = "Cámara mirrorless full-frame. Solo cuerpo. Ideal para eventos o sesiones de fotos puntuales. Requiere cuidado extremo.",
            dailyRate = 120.0,
            hourlyRate = 25.0,
            currentCondition = ItemCondition.NEW,
            isAvailable = true,
            imageUrl = "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?q=80&w=800&auto=format&fit=crop"
        ),
        Item(
            id = "item_003",
            ownerId = "user_103",
            categoryId = "c1",
            title = "Amoladora Angular Makita",
            description = "Amoladora de 4-1/2 pulgadas. Tiene desgaste estético pero el motor funciona al 100%. No incluye discos de corte.",
            dailyRate = 15.0,
            hourlyRate = 4.0,
            currentCondition = ItemCondition.FAIR,
            isAvailable = true,
            imageUrl = "https://images.unsplash.com/photo-1572981779307-38b8cabb2407?q=80&w=800&auto=format&fit=crop"
        )
    )
    // Funciones de prueba
    override fun getCategories(): Flow<List<Category>> {
        return flowOf(mockCategories)
    }

    override fun getFeaturedItems(): Flow<List<Item>> {
        return flowOf(mockItems.filter { it.isAvailable })
    }

    override fun getItemsByCategory(categoryId: String): Flow<List<Item>> {
        return flowOf(mockItems.filter { it.categoryId == categoryId })
    }

    override suspend fun getItemById(itemId: String): Item? {
        return mockItems.find { it.id == itemId }
    }
}