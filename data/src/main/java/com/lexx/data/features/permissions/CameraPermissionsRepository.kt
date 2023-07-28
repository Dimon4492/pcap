package com.lexx.data.features.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.lexx.domain.features.camera.PermissionsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CameraPermissionsRepository @Inject constructor(
    @ApplicationContext val context: Context
) : PermissionsRepository {
    private val cameraPermissionGrantedFlow = MutableStateFlow<Boolean>(false)

    override fun cameraPermissionsGranted(): Flow<Boolean> {
        return cameraPermissionGrantedFlow
    }

    override fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    cameraPermissionGrantedFlow.emit(true)
                }
            }
        }
    }

    init{
        checkPermissions()
    }
}
