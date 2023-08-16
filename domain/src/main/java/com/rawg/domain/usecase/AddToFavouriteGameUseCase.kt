package com.rawg.domain.usecase

import com.rawg.model.param.ParamGameDetail

interface AddToFavouriteGameUseCase {
    suspend operator fun invoke(param: ParamGameDetail): Long
}