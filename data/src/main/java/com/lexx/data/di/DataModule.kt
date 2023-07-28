package com.lexx.data.di

import com.lexx.data.features.permissions.CameraPermissionsRepository
import com.lexx.data.features.samba.RemoteSambaRepository
import com.lexx.data.features.settings.DataStoreSettingsRepository
import com.lexx.data.features.settings.MemoryLogRepository
import com.lexx.domain.features.camera.PermissionsRepository
import com.lexx.domain.features.logger.LogRepository
import com.lexx.domain.features.samba.SambaRepository
import com.lexx.domain.features.settings.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindUserPreferencesRepository(dataStoreUserPreferencesRepository: DataStoreSettingsRepository): SettingsRepository

    @Singleton
    @Binds
    abstract fun bindLogRepository(memoryLogRepository: MemoryLogRepository): LogRepository

    @Singleton
    @Binds
    abstract fun bindPermissionRepository(permissionsRepository: CameraPermissionsRepository): PermissionsRepository

    @Singleton
    @Binds
    abstract fun bindSambaRepository(sambaRepository: RemoteSambaRepository): SambaRepository
}
