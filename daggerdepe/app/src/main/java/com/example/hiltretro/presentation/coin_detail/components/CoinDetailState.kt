package com.example.hiltretro.presentation.coin_detail.components

import com.example.hiltretro.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)