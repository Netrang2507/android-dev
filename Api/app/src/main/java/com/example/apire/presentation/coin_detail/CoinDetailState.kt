package com.example.apire.presentation.coin_detail

import com.example.apire.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coin : CoinDetail? = null,
    val error: String = ""
)
