package com.bitpanda.livechallenge.data.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CryptoRxApi {

    @GET("/assets")
    fun getAssets(): Single<AssetResponse>
}