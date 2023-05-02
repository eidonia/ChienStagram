package com.exalt.api.di

import com.exalt.api.DummyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PostServiceModule {
    @Provides
    fun providePostService() = DummyApi().postService
}