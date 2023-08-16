package com.rawg.network.di

import com.rawg.network.BuildConfig
import com.rawg.network.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT = 10L
    const val RAWG_RETROFIT_WITH_API_KEY = "RAWG_RETROFIT_WITH_API_KEY"

    @Provides
    fun provideApiKeyInterceptor() = ApiKeyInterceptor()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    fun provideLoggedInOkHttp(
        apiKeyInterceptor: ApiKeyInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(apiKeyInterceptor)
        okHttpClient.addInterceptor(httpLoggingInterceptor).build()
        return okHttpClient.build()
    }

    @Provides
    @Named(RAWG_RETROFIT_WITH_API_KEY)
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}