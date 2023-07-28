package com.lexx.domain.features.camera

import kotlinx.coroutines.flow.Flow

interface PermissionsRepository {
    fun cameraPermissionsGranted(): Flow<Boolean>
    fun checkPermissions()
}
