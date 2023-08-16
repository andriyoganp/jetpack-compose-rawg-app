package com.rawg.domain.usecase

import com.rawg.data.repository.GameRepository
import javax.inject.Inject

internal class GetFavouriteGamesUseCaseImpl @Inject constructor(
    private val repository: GameRepository
) : GetFavouriteGamesUseCase {
    override fun invoke() = repository.getFavouriteGames()

}