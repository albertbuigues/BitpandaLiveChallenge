package com.bitpanda.livechallenge.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val changePercent24Hr: Double,
)

@Serializable
data class AssetResponse(
    val data: List<Asset>
)