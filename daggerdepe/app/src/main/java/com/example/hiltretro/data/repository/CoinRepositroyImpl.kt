package com.example.hiltretro.data.repository


import com.example.hiltretro.data.remote.CoinPaprikaApi
import com.example.hiltretro.data.remote.dto.CoinDetailsDto
import com.example.hiltretro.data.remote.dto.CoinDto
import com.example.hiltretro.domain.repository.CoinRepository
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