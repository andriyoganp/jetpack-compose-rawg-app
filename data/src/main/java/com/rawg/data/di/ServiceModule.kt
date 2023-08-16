package com.rawg.data.di

import com.rawg.data.service.GameService
import com.rawg.network.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideGameService(
        @Named(NetworkModule.RAWG_RETROFIT_WITH_API_KEY) retrofit: Retrofit,
    ): GameService {
        return retrofit.create(GameService::class.java)
    }
}