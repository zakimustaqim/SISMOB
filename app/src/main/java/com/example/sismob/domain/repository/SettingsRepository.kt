package com.example.sismob.domain.repository

import com.example.sismob.domain.model.ApiCredentials

interface SettingsRepository {
    suspend fun getApiInfo(apiActive: Boolean): List<ApiCredentials>
}
