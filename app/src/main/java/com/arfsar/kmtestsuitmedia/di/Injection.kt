package com.arfsar.kmtestsuitmedia.di

import android.content.Context
import com.arfsar.kmtestsuitmedia.data.repository.UserRepository
import com.arfsar.kmtestsuitmedia.data.retrofit.api.ApiConfig

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}