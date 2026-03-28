package com.presto.prezto.di

import com.presto.prezto.feature_explore.data.repository.MockExploreRepositoryImpl
import com.presto.prezto.feature_explore.domain.repository.ExploreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideExploreRepository(): ExploreRepository {
        return MockExploreRepositoryImpl()
    }

}