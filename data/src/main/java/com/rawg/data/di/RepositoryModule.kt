package com.rawg.data.di

import com.rawg.data.repository.GameRepository
import com.rawg.data.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun bindGameRepository(impl: GameRepositoryImpl): GameRepository
}