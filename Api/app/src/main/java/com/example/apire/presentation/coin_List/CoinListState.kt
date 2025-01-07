package com.example.apire.presentation.coin_List

import com.example.apire.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<Coin> = emptyList(),
    val error: String = ""
)
