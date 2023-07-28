package com.lexx.domain.features.settings

import javax.inject.Inject

class SetUsernameUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(login: String) {
        repository.setLogin(login = login)
    }
}
