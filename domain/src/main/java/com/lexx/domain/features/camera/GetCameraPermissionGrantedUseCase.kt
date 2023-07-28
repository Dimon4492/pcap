package com.lexx.domain.features.camera

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCameraPermissionGrantedUseCase @Inject constructor(
    val permissionsRepository: PermissionsRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        return permissionsRepository.cameraPermissionsGranted()
    }
}
