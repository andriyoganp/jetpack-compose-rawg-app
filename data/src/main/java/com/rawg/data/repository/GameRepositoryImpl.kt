package com.rawg.data.repository

import com.rawg.core.Resource
import com.rawg.core.utils.convertToJson
import com.rawg.data.dto.GameDetailDto
import com.rawg.data.dto.GameListDto
import com.rawg.data.service.GameService
import com.rawg.database.dao.FavouriteGameDao
import com.rawg.database.entity.FavouriteGameEntity
import com.rawg.model.Game
import com.rawg.model.param.ParamGameDetail
import com.rawg.model.param.ParamGameList
import com.rawg.network.ext.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val gameService: GameService,
    private val favouriteGameDao: FavouriteGameDao,
) : GameRepository {
    override suspend fun getGames(param: ParamGameList): Resource<List<Game>> {
        val map = mutableMapOf<String, String>()
        if (param.search.isNotBlank()) {
            map["search"] = param.search
        }
        map["page"] = param.page.toString()
        return safeApiCall(default = GameListDto(), {
            gameService.getGames(map)
        }, {
            it.results.orEmpty().map(GameDetailDto::toModel)
        })
    }

    override suspend fun getGameDetail(param: ParamGameDetail): Resource<Game> =
        safeApiCall(default = GameDetailDto(), {
            gameService.getGameDetail(param.slug)
        }, GameDetailDto::toModel)

    override fun getFavouriteGames(): Flow<List<Game>> =
        favouriteGameDao.getFavouriteGames().map { list ->
            list.map(FavouriteGameEntity::toModel)
        }

    override fun checkIsGameFavourite(param: ParamGameDetail): Flow<Game> {
        return favouriteGameDao.checkIsFavouriteGame(param.slug).map {
            it?.toModel() ?: Game.defaultValue
        }
    }

    override suspend fun addToFavouriteGame(param: ParamGameDetail): Long =
        favouriteGameDao.insertOrIgnoreFavouriteGame(
            FavouriteGameEntity(
                slug = param.slug,
                gameDetail = (param.game ?: "").convertToJson(),
                createdAt = Date()
            )
        )

    override suspend fun deleteFromFavouriteGame(param: ParamGameDetail): Int =
        favouriteGameDao.deleteFavouriteGame(param.slug)
}