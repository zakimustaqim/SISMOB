package com.example.sismob.di

import android.content.Context
import com.example.sismob.data.local.DataStoreManager
import com.example.sismob.data.remote.ApiService
import com.example.sismob.data.repository.AuthRepositoryImpl
import com.example.sismob.data.repository.SettingsRepositoryImpl
import com.example.sismob.domain.repository.AuthRepository
import com.example.sismob.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(): SettingsRepository {
        return SettingsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        settingsRepository: SettingsRepository
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, settingsRepository)
    }
}
