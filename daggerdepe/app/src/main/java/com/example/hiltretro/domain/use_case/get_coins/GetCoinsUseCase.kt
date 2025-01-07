package com.example.hiltretro.domain.use_case.get_coins

import android.annotation.SuppressLint
import android.net.http.HttpException
import com.example.hiltretro.common.Resource
import com.example.hiltretro.data.remote.dto.toCoin
import com.example.hiltretro.domain.model.Coin
import com.example.hiltretro.domain.repository.CoinRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch(@SuppressLint("NewApi") e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }
}