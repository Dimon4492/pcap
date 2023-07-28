package com.lexx.domain.features.preview

import com.lexx.domain.features.settings.SettingsRepository
import javax.inject.Inject

class GetLastFilenameUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return repository.getLastFilename()
    }
}
