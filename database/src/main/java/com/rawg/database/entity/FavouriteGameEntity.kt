package com.rawg.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rawg.core.utils.fromJson
import com.rawg.model.Game
import java.util.Date

@Entity(tableName = "favourite_games")
data class FavouriteGameEntity(
    @PrimaryKey
    val slug: String,
    @ColumnInfo(name = "game_detail")
    val gameDetail: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
) {
    fun toModel() : Game = gameDetail.fromJson()
}
