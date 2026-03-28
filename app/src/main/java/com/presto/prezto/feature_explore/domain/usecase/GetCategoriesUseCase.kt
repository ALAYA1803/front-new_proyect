package com.presto.prezto.feature_explore.domain.usecase

import com.presto.prezto.feature_explore.domain.model.Category
import com.presto.prezto.feature_explore.domain.repository.ExploreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ExploreRepository
) {
    // El operador invoke permite llamar a la instancia directamente: getCategoriesUseCase()
    operator fun invoke(): Flow<List<Category>> {
        // En una app real, aquí podríamos aplicar filtros antes de enviar a la vista
        return repository.getCategories()
    }
}