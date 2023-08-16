package com.rawg.domain.usecase

import com.rawg.data.repository.GameRepository
import com.rawg.model.param.ParamGameDetail
import javax.inject.Inject

internal class CheckIsFavouriteGameUseCaseImpl @Inject constructor(
    private val repository: GameRepository
) : CheckIsFavouriteGameUseCase {
    override fun invoke(param: ParamGameDetail) = repository.checkIsGameFavourite(param)
}