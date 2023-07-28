package com.lexx.domain.features.settings

import javax.inject.Inject

class SetOutputDirectoryUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(outputDirectory: String) {
        repository.setOutputDirectory(outputDirectory = outputDirectory)
    }
}
