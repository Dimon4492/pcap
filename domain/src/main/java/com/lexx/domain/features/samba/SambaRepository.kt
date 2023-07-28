package com.lexx.domain.features.samba

interface SambaRepository {
    fun copyFile(
        serverAddress: String,
        username: String,
        password: String,
        shareName: String,
        outputDirectory: String,
        filename: String
    )
}
