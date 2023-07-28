package com.lexx.domain.features.settings

import javax.inject.Inject

class SetFilenameSuffixUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(filenameSuffix: String) {
        repository.setFilenameSuffix(filenameSuffix = filenameSuffix)
    }
}
