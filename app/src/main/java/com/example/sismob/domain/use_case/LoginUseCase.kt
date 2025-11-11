package com.example.sismob.domain.use_case

import com.example.sismob.data.local.DataStoreManager
import com.example.sismob.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return authRepository.login(username, password).map { headerData ->
            dataStoreManager.saveUser(
                nik = headerData.datas?.Nik ?: "",
                name = headerData.datas?.Nama ?: "",
                dept = headerData.dept ?: "",
                token = headerData.access_token ?: ""
            )
        }
    }
}
