package com.example.apire.di

import android.provider.SyncStateContract.Constants
import com.example.apire.common.Constant
import com.example.apire.data.remote.CoinPaprikaApi
import com.example.apire.data.repository.CoinRepositoryImpl
import com.example.apire.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideParikaApi : CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api : CoinPaprikaApi):CoinRepository{
        return CoinRepositoryImpl(api)

    }


}