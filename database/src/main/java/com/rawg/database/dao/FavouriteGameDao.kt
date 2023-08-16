package com.rawg.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rawg.database.entity.FavouriteGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteGameDao {
    @Query("""SELECT * FROM favourite_games ORDER BY created_at DESC""")
    fun getFavouriteGames(): Flow<List<FavouriteGameEntity>>

    @Query("""SELECT * FROM favourite_games WHERE slug = :slug""")
    fun checkIsFavouriteGame(slug: String): Flow<FavouriteGameEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreFavouriteGame(entity: FavouriteGameEntity): Long

    @Query("""DELETE FROM favourite_games WHERE slug = :slug""")
    suspend fun deleteFavouriteGame(slug: String): Int
}