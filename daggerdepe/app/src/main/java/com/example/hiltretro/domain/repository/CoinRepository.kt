package com.example.hiltretro.domain.repository

import com.example.hiltretro.data.remote.dto.CoinDetailsDto
import com.example.hiltretro.data.remote.dto.CoinDto


interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailsDto
}