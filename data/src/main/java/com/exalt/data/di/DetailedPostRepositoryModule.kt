package com.exalt.data.di

import com.exalt.data.repositories.DetailedPostPreviewRepositoryImpl
import com.exalt.domain.post.repositories.DetailedPostPreviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailedPostRepositoryModule {
    @Binds
    internal abstract fun bindDetailedPostRepository(impl: DetailedPostPreviewRepositoryImpl): DetailedPostPreviewRepository
}