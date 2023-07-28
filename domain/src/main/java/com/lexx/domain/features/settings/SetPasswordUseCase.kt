package com.lexx.domain.features.settings

import javax.inject.Inject

class SetPasswordUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(password: String) {
        repository.setPassword(password = password)
    }
}
