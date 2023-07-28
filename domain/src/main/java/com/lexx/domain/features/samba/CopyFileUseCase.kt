package com.lexx.domain.features.samba

import com.lexx.domain.features.logger.LogReportUseCase
import com.lexx.domain.features.settings.GetOutputDirectoryUseCase
import com.lexx.domain.features.settings.GetPasswordUseCase
import com.lexx.domain.features.settings.GetServerAddressUseCase
import com.lexx.domain.features.settings.GetShareNameUseCase
import com.lexx.domain.features.settings.GetUsernameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CopyFileUseCase @Inject constructor(
    val sambaRepository: SambaRepository,
    val getServerAddressUseCase: GetServerAddressUseCase,
    val getUsernameUseCase: GetUsernameUseCase,
    val getPasswordUseCase: GetPasswordUseCase,
    val getShareNameUseCase: GetShareNameUseCase,
    val getOutputDirectoryUseCase: GetOutputDirectoryUseCase,
    val logReportUseCase: LogReportUseCase,
)  {
    operator fun invoke(
        filename: String,
    ) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                sambaRepository.copyFile(
                    getServerAddressUseCase(),
                    getUsernameUseCase(),
                    getPasswordUseCase(),
                    getShareNameUseCase(),
                    getOutputDirectoryUseCase(),
                    filename,
                )
            }
        }
    }
}
