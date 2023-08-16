package com.rawg.database.di

import android.content.Context
import androidx.room.Room
import com.rawg.database.RAWGDatabase
import com.rawg.database.dao.FavouriteGameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "rawg-database"

    @Provides
    @Singleton
    fun provideRAWGDatabase(
        @ApplicationContext context: Context,
    ): RAWGDatabase =
        Room.databaseBuilder(context, RAWGDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun providesFavouriteGameDao(
        database: RAWGDatabase
    ): FavouriteGameDao = database.favouriteGameDao()
}