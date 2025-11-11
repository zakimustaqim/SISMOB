package com.example.sismob.domain.use_case

import com.example.sismob.data.local.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    operator fun invoke(): Flow<Boolean> {
        return dataStoreManager.isLogin
    }
}
