package com.example.sismob.domain.use_case

import android.content.Context
import com.example.sismob.util.DeviceUtil
import com.example.sismob.util.NotificationUtil
import javax.inject.Inject

class GetDeviceDetailsUseCase @Inject constructor(
    private val context: Context
) {
    suspend operator fun invoke(): DeviceDetails {
        val advertisingId = DeviceUtil.getAdvertisingId(context)
        val localIpAddress = DeviceUtil.getLocalIpAddress()
        val fcmToken = NotificationUtil.getFCMToken()
        return DeviceDetails(advertisingId, localIpAddress, fcmToken)
    }
}

data class DeviceDetails(
    val advertisingId: String,
    val localIpAddress: String,
    val fcmToken: String
)
