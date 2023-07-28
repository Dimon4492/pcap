package com.lexx.domain.features.preview

import com.lexx.domain.features.settings.SettingsRepository
import javax.inject.Inject

class SetLastFilenameUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(filename: String) {
        repository.setLastFilename(filename = filename)
    }
}
