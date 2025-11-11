package com.example.sismob.domain.repository

import com.example.sismob.data.remote.dto.HeaderData
import com.example.sismob.data.remote.dto.MsgInfo

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<HeaderData>
    suspend fun postData(paramNamePost: String): Result<List<MsgInfo>>
}
