package com.example.apire.data.repository

import com.example.apire.data.remote.CoinPaprikaApi
import com.example.apire.data.remote.dto.CoinDetailsDto
import com.example.apire.data.remote.dto.CoinDto
import com.example.apire.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }
}