package com.exalt.data.di

import com.exalt.data.repositories.ProfileRepositoryImpl
import com.exalt.domain.profile.repositories.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileRepositoryModule {

    @Binds
    internal abstract fun provideProfileRpository(impl: ProfileRepositoryImpl): ProfileRepository
}