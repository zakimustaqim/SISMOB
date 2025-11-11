package com.example.sismob.domain.use_case

import android.content.Context
import com.example.sismob.util.PermissionUtil
import javax.inject.Inject

class CheckPermissionsUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(): Boolean {
        return PermissionUtil.hasCameraPermission(context) && PermissionUtil.hasLocationPermission(
            context
        )
    }
}
