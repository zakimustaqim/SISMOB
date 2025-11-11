package com.example.sismob.domain.use_case

import com.example.sismob.data.local.DataStoreManager
import com.example.sismob.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SendDeviceDetailsUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager,
    private val getDeviceDetailsUseCase: GetDeviceDetailsUseCase
) {
    suspend operator fun invoke() {
        val deviceDetails = getDeviceDetailsUseCase()
        val nik = dataStoreManager.nik.first() ?: ""
        val listParam =
            "mobMst_Device_Post '${deviceDetails.advertisingId}','${deviceDetails.localIpAddress}','${deviceDetails.fcmToken}','${nik}'"
        authRepository.postData(listParam)
    }
}
