package com.lexx.domain.features.settings

import javax.inject.Inject

class GetPasswordUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return repository.getPassword()
    }
}
