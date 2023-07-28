package com.lexx.domain.features.settings

import javax.inject.Inject

class SetShareNameUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(shareName: String) {
        repository.setShareName(shareName = shareName)
    }
}
