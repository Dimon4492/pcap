package com.lexx.data.features.samba

import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import com.lexx.domain.features.logger.LogReportUseCase
import com.lexx.domain.features.samba.SambaRepository
import jcifs.CIFSContext
import jcifs.config.PropertyConfiguration
import jcifs.context.BaseContext
import jcifs.smb.NtlmPasswordAuthenticator
import jcifs.smb.SmbFile
import jcifs.smb.SmbFileOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class RemoteSambaRepository @Inject constructor(
    val logReportUseCase: LogReportUseCase
) : SambaRepository {
    override fun copyFile(
        serverAddress: String,
        username: String,
        password: String,
        shareName: String,
        outputDirectory: String,
        filenameFullPath: String
    ) {
        try {
            val baseCxt: CIFSContext = BaseContext(PropertyConfiguration(System.getProperties()))
            val auth = NtlmPasswordAuthenticator(username, password)
            val ct: CIFSContext = baseCxt.withCredentials(auth)
            val filename = Uri.parse(filenameFullPath).lastPathSegment!!
            val smbFile = SmbFile("smb://$serverAddress/$shareName/$outputDirectory/$filename", ct)

            val outputSmbFileStream = SmbFileOutputStream(smbFile)

            val localFile: File = Uri.parse("file://$filenameFullPath").toFile()
            val inputFileStream = FileInputStream(localFile)

            val buffer = ByteArray(4096)
            var length = 0
            while (inputFileStream.read(buffer).also { length = it } > 0) {
                outputSmbFileStream.write(buffer, 0, length)
            }

            outputSmbFileStream.close()
            inputFileStream.close()
        } catch (e: Exception) {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    logReportUseCase("exception: $e")
                }
            }
        }
    }
}
