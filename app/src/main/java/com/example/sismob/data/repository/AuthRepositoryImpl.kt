package com.example.sismob.data.repository

import android.util.Log
import com.example.sismob.data.remote.ApiService
import com.example.sismob.data.remote.dto.HeaderData
import com.example.sismob.data.remote.dto.MsgInfo
import com.example.sismob.domain.repository.AuthRepository
import com.example.sismob.domain.repository.SettingsRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val settingsRepository: SettingsRepository
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<HeaderData> {
        return try {
            val response = apiService.login(username, password)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postData(paramNamePost: String): Result<List<MsgInfo>> {
        return try {
            val fullUrl = getFullUrl()
            val response = apiService.postData(fullUrl, paramNamePost)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception("API call failed: ${'$'}{response.code()} - ${'$'}{errorBody ?: response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Failed to fetch data of type '${'$'}paramNamePost': ${'$'}{e.message}", e))
        }
    }

    private suspend fun getFullUrl(): String {
        val credentialsList = settingsRepository.getApiInfo(apiActive = true)
        val activeCredentials = credentialsList.firstOrNull()
            ?: throw IllegalStateException("No active database credentials found.")
        return activeCredentials.apiUrl
    }
}
