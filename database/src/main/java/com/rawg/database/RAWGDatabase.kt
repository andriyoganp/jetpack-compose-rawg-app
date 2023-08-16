package com.rawg.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rawg.database.converter.Converter
import com.rawg.database.dao.FavouriteGameDao
import com.rawg.database.entity.FavouriteGameEntity

@Database(
    entities = [FavouriteGameEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class RAWGDatabase : RoomDatabase() {
    abstract fun favouriteGameDao(): FavouriteGameDao
}