package com.example.sismob.data.repository

import com.example.sismob.domain.model.ApiCredentials
import com.example.sismob.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor() : SettingsRepository {

    override suspend fun getApiInfo(apiActive: Boolean): List<ApiCredentials> {
        // For now, we'll return a hardcoded list.
        // In a real application, this would come from a local database or DataStore.
        return listOf(
            ApiCredentials(apiUrl = "https://api.sfm.co.id/api/")
        )
    }
}
