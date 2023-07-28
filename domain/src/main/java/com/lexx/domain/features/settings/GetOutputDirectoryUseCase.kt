package com.lexx.domain.features.settings

import javax.inject.Inject

class GetOutputDirectoryUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return repository.getOutputDirectory()
    }
}
