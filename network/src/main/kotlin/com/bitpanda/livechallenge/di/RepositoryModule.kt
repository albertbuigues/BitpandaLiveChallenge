package com.bitpanda.livechallenge.di

import com.bitpanda.livechallenge.domain.repository.CryptosRepository
import com.bitpanda.livechallenge.repository.CryptoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCryptoRepository(impl: CryptoRepositoryImpl): CryptosRepository
}