package com.example.apire.domain.repository

import android.adservices.adid.AdId
import com.example.apire.data.remote.dto.CoinDetailsDto
import com.example.apire.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String):CoinDetailsDto
}