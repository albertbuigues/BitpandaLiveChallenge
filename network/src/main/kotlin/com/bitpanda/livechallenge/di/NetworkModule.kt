package com.bitpanda.livechallenge.di

import com.bitpanda.livechallenge.api.CryptoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    @Provides
    fun provideRetrofit(json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rest.coincap.io/v3/")
            .addConverterFactory(
                json.asConverterFactory(MediaType.get("application/json"))
            )
            .build()
    }

    @Provides
    fun provideCryptoCoroutinesApi(retrofit: Retrofit): CryptoApi {
        return retrofit.create()
    }
}