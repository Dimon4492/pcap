package com.lexx.presentation.ui.camera

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexx.presentation.R
import com.lexx.presentation.ui.camera.util.getCameraProvider
import com.lexx.presentation.ui.camera.util.getOutputDirectory
import com.lexx.presentation.ui.camera.util.takePhoto
import com.lexx.presentation.ui.preview.PreviewPageViewModel
import java.util.concurrent.Executors

@Composable
fun CameraPage(
    modifier: Modifier = Modifier,
    onPreview: () -> Unit,
    cameraPageViewModel: CameraPageViewModel = viewModel(),
) {
    val uiState = cameraPageViewModel.uiState.collectAsState().value
    if (uiState.permissionGranted) {
        CameraView(
            onPreview,
            modifier,
        )
    } else {
        Text(text = stringResource(id = R.string.camera_permissions_required))
    }
}

@Composable
fun CameraView(
    onPreview: () -> Unit,
    modifier: Modifier = Modifier,
    cameraPageViewModel: CameraPageViewModel = viewModel(),
    previewPageViewModel: PreviewPageViewModel = viewModel(),
) {
    val uiState = cameraPageViewModel.uiState.collectAsState().value

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(uiState.lensFacing)
        .build()

    LaunchedEffect(uiState.lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier,
    ) {
        AndroidView(
            { previewView },
        )

        IconButton(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = {
                takePhoto(
                    filenamePrefix = uiState.prefix,
                    filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
                    imageCapture = imageCapture,
                    outputDirectory = getOutputDirectory(context),
                    executor = Executors.newSingleThreadExecutor(),
                    onImageCaptured = {
                        cameraPageViewModel.onImageCaptured(
                            it,
                            onPreview
                        ) { previewPageViewModel.updateData() }
                    },
                    onError = {
                        cameraPageViewModel.onError(
                            it,
                            onPreview
                        ) { previewPageViewModel.updateData() }

                    },
                )
            },
            content = {
                Icon(
                    imageVector = Icons.Sharp.Lens,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        )
    }
}