package com.lexx.domain.features.camera

import javax.inject.Inject

class CheckPermissionUseCase @Inject constructor(
    val permissionsRepository: PermissionsRepository,
)  {
    operator fun invoke() {
        return permissionsRepository.checkPermissions()
    }
}
