package com.bitpanda.livechallenge.data.remote

import retrofit2.http.GET

interface CryptoCoroutinesApi {

    @GET("/assets")
    suspend fun getAsset(): AssetResponse
}