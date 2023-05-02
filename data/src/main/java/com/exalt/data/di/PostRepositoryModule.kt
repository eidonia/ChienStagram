package com.exalt.data.di

import com.exalt.data.repositories.PostPreviewRepositoryImpl
import com.exalt.domain.home.repositories.PostPreviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PostRepositoryModule {

    @Binds
    internal abstract fun bindPostRepository(impl: PostPreviewRepositoryImpl): PostPreviewRepository
}