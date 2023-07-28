package com.lexx.domain.features.settings

import javax.inject.Inject

class GetFilenameSuffixUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return repository.getFilenameSuffix()
    }
}
