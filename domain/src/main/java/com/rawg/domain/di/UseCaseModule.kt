package com.rawg.domain.di

import com.rawg.domain.usecase.AddToFavouriteGameUseCase
import com.rawg.domain.usecase.AddToFavouriteGameUseCaseImpl
import com.rawg.domain.usecase.CheckIsFavouriteGameUseCase
import com.rawg.domain.usecase.CheckIsFavouriteGameUseCaseImpl
import com.rawg.domain.usecase.DeleteFromFavouriteUseCase
import com.rawg.domain.usecase.DeleteFromFavouriteUseCaseImpl
import com.rawg.domain.usecase.GetFavouriteGamesUseCase
import com.rawg.domain.usecase.GetFavouriteGamesUseCaseImpl
import com.rawg.domain.usecase.GetGameDetailUseCase
import com.rawg.domain.usecase.GetGameDetailUseCaseImpl
import com.rawg.domain.usecase.GetGamesUseCase
import com.rawg.domain.usecase.GetGamesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    internal abstract fun bindAddToFavouriteGameUseCase(
        impl: AddToFavouriteGameUseCaseImpl,
    ): AddToFavouriteGameUseCase

    @Binds
    internal abstract fun bindDeleteFromFavouriteUseCase(
        impl: DeleteFromFavouriteUseCaseImpl,
    ): DeleteFromFavouriteUseCase

    @Binds
    internal abstract fun bindCheckIsFavouriteGame(
        impl: CheckIsFavouriteGameUseCaseImpl,
    ): CheckIsFavouriteGameUseCase

    @Binds
    internal abstract fun bindGetFavouriteGamesUseCase(
        impl: GetFavouriteGamesUseCaseImpl,
    ): GetFavouriteGamesUseCase

    @Binds
    internal abstract fun bindGetGamesUseCase(impl: GetGamesUseCaseImpl): GetGamesUseCase

    @Binds
    internal abstract fun bindGetGameDetailUseCase(impl: GetGameDetailUseCaseImpl): GetGameDetailUseCase
}